package com.alexereh.cocktailbar.ui.createcocktail

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.alexereh.cocktailbar.components.createcocktail.CreateCocktailComponent
import com.alexereh.cocktailbar.components.createcocktail.FakeCreateCocktailComponent
import com.alexereh.cocktailbar.ui.customui.CustomButton
import com.alexereh.cocktailbar.ui.customui.CustomImage
import com.alexereh.cocktailbar.ui.customui.CustomTextField
import com.alexereh.cocktailbar.ui.theme.CocktailBarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCocktailScreen(
    component: CreateCocktailComponent
) {
    val title by component.title.collectAsState()
    val description by component.description.collectAsState()
    val recipe by component.recipe.collectAsState()
    val ingredients by component.ingredients.collectAsState()
    val uri by component.uri.collectAsState()


    val ingredientName = remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            properties = DialogProperties(dismissOnClickOutside = false),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        "Add ingredient",
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    CustomTextField(
                        label = "Ingredient",
                        value = ingredientName.value,
                        onValueChange = {
                            ingredientName.value = it
                        },
                        mustNotBeEmpty = true
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    CustomButton(
                        onClick = {
                            if (ingredientName.value.trim() != "") {
                                component.addIngredient(ingredientName.value)
                                ingredientName.value = ""
                                openDialog.value = false
                            }
                        },
                        text = "Add",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomButton(
                        onClick = {
                            ingredientName.value = ""
                            openDialog.value = false
                        },
                        text = "Cancel",
                        outlined = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }

    val scrollState = rememberScrollState()
    val launcher = rememberLauncherForActivityResult(PickVisualMedia()) {
        component.updatePhoto(it)
    }

    Column(
        modifier = Modifier
            //.fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        CustomImage(
            uri = uri,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(54.dp)),
            onClick = {
                launcher.launch(
                    PickVisualMediaRequest(
                        mediaType = PickVisualMedia.ImageOnly
                    )
                )
            }
        )
        CustomTextField(
            label = "Title",
            mustNotBeEmpty = true,
            value = title,
            singleLine = true,
            onValueChange = component::updateTitle,
            modifier = Modifier
                .fillMaxWidth()
        )
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = "Description",
            minHeight = 200.dp,
            value = description,
            onValueChange = component::updateDescription,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(ingredients) {
                InputChip(
                    selected = false,
                    onClick = { component.removeIngredient(it) },
                    label = {
                        Text(it)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null
                        )
                    },
                    shape = CircleShape,
                    colors = InputChipDefaults.inputChipColors(
                        trailingIconColor = Color(0xFF4B97FF)
                    )
                )
            }
            item {
                IconButton(
                    onClick = { openDialog.value = true },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFF4B97FF),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = "Recipe",
            minHeight = 200.dp,
            value = recipe,
            onValueChange = component::updateRecipe
        )
        CustomButton(
            text = "Save",
            onClick = {
                if (title.trim() != "") component.save()
            },
            modifier = Modifier.fillMaxWidth()
        )

        CustomButton(
            text = "Cancel",
            outlined = true,
            onClick = component::cancel,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview
@Composable
fun CreateCocktailPreview() {
    CocktailBarTheme {
        CreateCocktailScreen(component = FakeCreateCocktailComponent())
    }
}