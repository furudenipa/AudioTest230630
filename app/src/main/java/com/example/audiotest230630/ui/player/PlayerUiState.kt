package com.example.audiotest230630.ui.player

import com.example.audiotest230630.data.AudioContent
import com.example.audiotest230630.data.Track

data class PlayerUiState(
    val selectedAudioContent: AudioContent,
    val index: Int,
    val isPlaying: Boolean
)
