package com.alexereh.cocktailbar.util

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class CoroutineRetainedInstance(mainContext: CoroutineContext) : InstanceKeeper.Instance {
    // The scope survives Android configuration changes
    private val scope = CoroutineScope(mainContext + SupervisorJob())

    fun launch(func: suspend () -> Unit) {
        scope.launch {
            func()
        }
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }
}