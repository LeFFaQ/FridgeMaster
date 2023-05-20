package ru.lffq.fmaster.ui.base.cache

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CacheView(vm: CacheViewModel) {
    val loadingState by vm.loadingState.collectAsState(CacheLoadingState.Initial)


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .align(Alignment.Center)
            .animateContentSize()) {
            if (loadingState !is CacheLoadingState.Success) {
                CircularProgressIndicator()
            }
            Text(text = loadingState.text)
        }

        if (loadingState is CacheLoadingState.Success) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Продолжить")
            }
        }

    }
}