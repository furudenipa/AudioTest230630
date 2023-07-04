package com.example.audiotest230630.data

import com.example.audiotest230630.R
import com.example.audiotest230630.data.SampleTrack.initTrack
import com.example.audiotest230630.data.SampleTrack.tes1
import com.example.audiotest230630.data.SampleTrack.tes2
import com.example.audiotest230630.data.SampleTrack.tes3
import com.example.audiotest230630.data.SampleTrack.tes4
import com.example.audiotest230630.data.SampleTrack.tes5
import com.example.audiotest230630.data.SampleTrack.tes6

object SampleAudioContent {
    val album1: Album = Album(id = "",title = "album1", coverImage = R.drawable.neko1.toString(), audioObjects = listOf(tes1,tes2),)
    val single1: Single = Single(id = "", title = "single1", coverImage =  R.drawable.neko2.toString(), audioObjects = listOf(tes3))
    val userPlayList1: UserPlaylist = UserPlaylist(id = "", title = "userPlayList1", coverImage = R.drawable.neko2.toString(), audioObjects = listOf(tes4,tes5,tes6))

    val initPlayList: UserPlaylist = UserPlaylist(id = "", title = "this is initPlayList", coverImage = R.drawable.neko3.toString(), audioObjects = listOf(initTrack))
}