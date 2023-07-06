package com.example.audiotest230630.ui.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material.icons.sharp.Pause
import androidx.compose.material.icons.sharp.SkipNext
import androidx.compose.material.icons.sharp.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.audiotest230630.data.SampleAudioContent.album1
import com.example.audiotest230630.viewmodel.PlayerViewModel
import com.google.android.exoplayer2.ExoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//https://engawapg.net/jetpack-compose/1946/lifecycleeventobserver/
@Composable
fun ObserveLifecycleEvent(onEvent: (Lifecycle.Event) -> Unit = {}) {
    // Safely update the current lambdas when a new one is provided
    val currentOnEvent by rememberUpdatedState(onEvent)
    val lifecycleOwner = LocalLifecycleOwner.current

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            currentOnEvent(event)
        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}


@Composable
fun PlayerScreen() {
    val playerViewModel: PlayerViewModel = viewModel()
    val playerUiState by playerViewModel.playerUiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        ObserveLifecycleEvent { event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    // アプリがフォアグラウンドに戻ったとき、音声を再生する
                    //playerViewModel.startPlayback(Uri.parse("https://amachamusic.chagasi.com/mp3/natsuyasuminotanken.mp3"))
                }

                Lifecycle.Event.ON_PAUSE -> {
                    // アプリがバックグラウンドに移動したとき、音声を一時停止する
                    playerViewModel.stop()
                }

                else -> {
                    // 他のライフサイクルイベントは無視
                }
            }
        }

        Button(onClick = {playerViewModel.play()}){
            Text(text = "再生")
        }

        IconButton(onClick = {playerViewModel.play()}){
            Icon(Icons.Sharp.PlayArrow,
                contentDescription = "hoge",
            )
        }

        IconButton(onClick = {playerViewModel.stop()}){
            Icon(Icons.Sharp.Pause,
                contentDescription = "hoge"
            )
        }

        Row() {
            IconButton(onClick = {playerViewModel.stop()}){
                Icon(Icons.Sharp.Pause,
                    contentDescription = "hoge"
                )
            }
            IconButton(onClick = {playerViewModel.seekToNextTrack()}){
                Icon(Icons.Sharp.SkipNext,
                    contentDescription = "hoge"
                )
            }
            IconButton(onClick = {playerViewModel.seekToPrevTrack()}){
                Icon(Icons.Sharp.SkipPrevious,
                    contentDescription = "hoge"
                )
            }
        }

        Button(onClick = {playerViewModel.seekToNextTrack()}){
            Text(text = "move to next")
        }
        Button(onClick = {playerViewModel.setAudioContent(album1)}){
            Text(text = "setaudioontent ")
        }
        Text(text = playerUiState.isPlaying.toString())

        playerSeekBar(playerViewModel)
    }
}

@Composable
fun playerSeekBar(
    playerViewModel: PlayerViewModel
){
    val coroutineScope = rememberCoroutineScope()

    // playerの再生位置と最大の長さ
    val position: Float = playerViewModel.currentPosition()
    val max: Float = playerViewModel.duration()

    // Sliderの値を監視する状態変数
    var sliderPosition by remember { mutableStateOf(position) }

    // Sliderの値が変更された場合のコールバックを設定
    val onSliderValueChanged = { value: Float ->
        sliderPosition = value
    }

    Slider(
        value = sliderPosition,
        onValueChange = { value -> onSliderValueChanged(value)},
        valueRange = 0f..max,
        steps = 0,
        modifier = Modifier.fillMaxWidth()
    )
    Text(text = sliderPosition.toString())


}
