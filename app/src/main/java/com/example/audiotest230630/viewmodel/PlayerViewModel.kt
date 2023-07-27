package com.example.audiotest230630.viewmodel

import androidx.lifecycle.ViewModel
import com.example.audiotest230630.data.AudioContent
import com.example.audiotest230630.data.SampleAudioContent
import com.example.audiotest230630.model.player.AudioPlayer
import com.example.audiotest230630.ui.player.PlayerUiState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerViewModel: ViewModel() {
    private val player = AudioPlayer()
    private val _playerUiState = MutableStateFlow(
        PlayerUiState(
            selectedAudioContent = SampleAudioContent.album1,
            index = 0,
            isPlaying = false,
            duration = 0f
        )
    )
    private val playerEventListener = object : Player.Listener
    {
        override fun onPlaybackStateChanged(playbackState: Int){
            super.onPlaybackStateChanged(playbackState)
            if (playbackState==ExoPlayer.STATE_READY){
                _playerUiState.value =
                    PlayerUiState(
                        selectedAudioContent = playerUiState.value.selectedAudioContent,
                        index = playerUiState.value.index,
                        isPlaying = playerUiState.value.isPlaying,
                        duration = player.duration.toFloat()
                    )
                println(player.duration.toFloat())
            }
        }
    }


    val playerUiState: StateFlow<PlayerUiState> = _playerUiState.asStateFlow()

    init{
        val mediaItems = playerUiState.value.selectedAudioContent.audioObjects.map { track ->
            MediaItem.fromUri(track.url)
        }
        player.setMediaItems(mediaItems, true)
        player.addListener(playerEventListener)

    }


    fun setAudioContent(audioContent: AudioContent){
        _playerUiState.value = PlayerUiState(
            selectedAudioContent = audioContent,
            index = 0,
            isPlaying = false,
            duration = playerUiState.value.duration

        )

        val mediaItems = playerUiState.value.selectedAudioContent.audioObjects.map { track ->
            MediaItem.fromUri(track.url)
        }
        player.setMediaItems(mediaItems, true)

    }

    fun play() {
        player.prepare()
        player.play()
        _playerUiState.value = PlayerUiState(
            selectedAudioContent = playerUiState.value.selectedAudioContent,
            index = playerUiState.value.index,
            isPlaying = true,
            duration = playerUiState.value.duration

        )
    }

    fun stop() {
        player.stop()
        _playerUiState.value = PlayerUiState(
            selectedAudioContent = playerUiState.value.selectedAudioContent,
            index = playerUiState.value.index,
            isPlaying = false,
            duration = playerUiState.value.duration
        )
    }

    fun seekTo(sliderPosition: Long){
        player.seekTo(sliderPosition)
        println("seekTO")

    }


    fun pausePlayback() {
        player.playWhenReady = false
    }

    fun resumePlayback() {
        player.playWhenReady = true
    }

    fun seekToNextTrack() {
        player.seekToNextMediaItem()
        //_playerUiState.value = PlayerUiState(
        //    selectedAudioContent = playerUiState.value.selectedAudioContent,
        //    index = player.currentMediaItemIndex,
         //   isPlaying = false
        //)
    }

    fun seekToPrevTrack() {
        player.seekToPreviousMediaItem()
        //_playerUiState.value = PlayerUiState(
         //   selectedAudioContent = playerUiState.value.selectedAudioContent,
         //   index = player.currentMediaItemIndex,
         //   isPlaying = false
        //)
    }


    fun currentPosition(): Float{
        return if (player.playbackState == Player.STATE_READY) {
            player.currentPosition.toFloat()
        } else {
            0f
        }
    }

    fun duration(): Float{
        return if (player.playbackState == Player.STATE_READY) {
            player.duration.toFloat()
        } else {
            1f
        }
    }


    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}