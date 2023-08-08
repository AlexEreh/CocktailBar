package com.alexereh.cocktailbar.components.createcocktail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.alexereh.cocktailbar.util.CoroutineRetainedInstance
import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.repo.CocktailRepository
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import okhttp3.internal.toImmutableList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date


class RealCreateCocktailComponent(
    private val navigateBack: () -> Unit,
    private val cocktailId: Int,
    componentContext: ComponentContext
): CreateCocktailComponent, KoinComponent,
    ComponentContext by componentContext {
    private val coroutineRetainedInstance = instanceKeeper.getOrCreate { CoroutineRetainedInstance(Dispatchers.IO) }

    private val repo: CocktailRepository by inject()
    private val context: Context by inject()

    private val _ingredients = MutableStateFlow(emptyList<String>())
    override val ingredients: StateFlow<List<String>>
        get() = _ingredients

    private val _title = MutableStateFlow("")
    override val title: StateFlow<String>
        get() = _title

    private val _description = MutableStateFlow("")
    override val description: StateFlow<String>
        get() = _description

    private val _recipe = MutableStateFlow("")
    override val recipe: StateFlow<String>
        get() = _recipe

    private val _uri = MutableStateFlow<String?>(null)
    override val uri: StateFlow<String?>
        get() = _uri

    private val _imgChanged = MutableStateFlow(false)

    override fun updateTitle(newText: String) {
        _title.update { newText }
    }

    override fun updateDescription(newText: String) {
        _description.update { newText }
    }

    override fun updateRecipe(newText: String) {
        _recipe.update { newText }
    }

    override fun updatePhoto(newUri: Uri?) {
        if (newUri != null) {
            _uri.update { newUri.toString() }
        }
    }

    override fun addIngredient(value: String) {
        if (value.trim() == "") return
        _ingredients.update {
            if (!it.contains(value)) it + value else it
        }
    }

    override fun removeIngredient(value: String) {
        _ingredients.update { it.minus(value) }
    }

    /** Create a File for saving an image or video  */
    private fun getOutputMediaFile(): File {
        // Create a media file name
        val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmm").format(Date())
        val mImageName = "MI_$timeStamp.jpg"
        return File(
            context.filesDir, mImageName
        )
    }

    private fun getCapturedImage(context: Context, selectedPhotoUri: Uri): Bitmap {
        return when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                context.contentResolver,
                selectedPhotoUri
            )
            else -> {
                val source = ImageDecoder.createSource(context.contentResolver, selectedPhotoUri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    override fun save() {
        val id = cocktailId
        if (_title.value.trim() == "") return
        runBlocking(Dispatchers.IO) {
            if (_uri.value != null && _imgChanged.value) {
                //val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.fromFile(File(_uri.value!!)))
                val bitmap: Bitmap = getCapturedImage(context, Uri.parse(_uri.value!!))
                val file = getOutputMediaFile()
                _uri.update { file.path }
                try {
                    FileOutputStream(file.path).use { out ->
                        bitmap.compress(
                            Bitmap.CompressFormat.PNG,
                            100,
                            out
                        ) // bmp is your Bitmap instance
                    }
                } catch (e: IOException) {
                    Log.e("", e.message!!)
                }
            }
            repo.createEditCocktail(
                Cocktail(
                    id,
                    _title.value,
                    if (_description.value == "") null else _description.value,
                    if (_recipe.value == "") null else _recipe.value,
                    _uri.value,
                    _ingredients.value.toList().toImmutableList()
                )
            )
        }
        navigateBack()
    }

    override fun cancel() {
        navigateBack()
    }

    init {
        lifecycle.doOnCreate{
            Log.d("CreateCocktail","$cocktailId")
            if (cocktailId != 0) {
                //Log.d("CreateCocktail","$cocktailId")
                coroutineRetainedInstance.launch {
                    val cocktailFromRepo = repo.getCocktail(cocktailId)
                    cocktailFromRepo.collect { cocktail ->
                        _uri.update { cocktail.imageUri }
                        _ingredients.update { cocktail.ingredientsList }
                        _title.update { cocktail.title }
                        cocktail.recipe?.let {
                            _recipe.update { cocktail.recipe }
                        }
                        cocktail.description?.let {
                            _description.update { cocktail.description }
                        }
                    }
                }
            }
        }
    }
}