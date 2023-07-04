package com.example.audiotest230630.data

enum class MediaType {
    SINGLE,
    ALBUM,
    PLAYLIST
}

interface AudioContent {
    val id: String
    val title: String
    val mediaType: MediaType
    val coverImage: String // 画像のURLを想定
    val audioObjects: List<Track>
}

data class Single(
    override val id: String,
    override val title: String,
    override val coverImage: String,
    override val audioObjects: List<Track>
) : AudioContent {
    override val mediaType: MediaType = MediaType.SINGLE
}

data class Album(
    override val id: String,
    override val title: String,
    override val coverImage: String,
    override val audioObjects: List<Track>,
    val discId: String = ""
) : AudioContent {
    override val mediaType: MediaType = MediaType.ALBUM
}

data class UserPlaylist(
    override val id: String,
    override val title: String,
    override val coverImage: String,
    override val audioObjects: List<Track>
) : AudioContent {
    override val mediaType: MediaType = MediaType.PLAYLIST
}
