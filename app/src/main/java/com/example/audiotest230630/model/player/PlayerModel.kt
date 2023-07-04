package com.example.audiotest230630.model.player

import android.app.Application
import android.content.Context
import com.example.audiotest230630.MyApplication
import com.google.android.exoplayer2.ExoPlayer

class AudioPlayer : ExoPlayer by ExoPlayer.Builder(MyApplication.instance.applicationContext).build() {

    companion object {
        fun getAppContext(): Context {
            return Application().applicationContext
        }
    }
}