package com.example.audiotest230630.data

object SampleTrack {
    val nekoHacker = Artist(id = 0, name = "neko hacker", role = Role.Composer)
    val hoge1 = Artist(id = 1, name = "hoge1", role = Role.Singer)
    val hoge2 = Artist(id = 2, name = "hoge2", role = Role.Singer)

    val tes1 = Track(
        url = "https://nekohacker.com/wp-content/uploads/2021/01/02-%E3%81%8F%E3%81%84%E3%81%97%E3%82%93%E3%81%BB%E3%82%99%E3%83%8F%E3%83%83%E3%82%AB%E3%83%BC-feat.-%E3%81%8F%E3%81%84%E3%81%97%E3%82%93%E3%81%BB%E3%82%99%E3%81%82%E3%81%8B%E3%81%A1%E3%82%83%E3%82%93.mp3",
        name = "02 くいしんぼハッカー feat. くいしんぼあかちゃん", artist = listOf(nekoHacker,hoge1))
    val tes2 = Track(url = "https://nekohacker.com/wp-content/uploads/2021/01/03-Home-Sweet-Home-feat.-KMNZ-LIZ.mp3",
        name = "03 Home Sweet Home feat. KMNZ LIZ", artist = listOf(nekoHacker,hoge1))
    val tes3 = Track(url = "https://nekohacker.com/wp-content/uploads/2021/01/04-Hack-You-feat.-%E3%81%86%E3%81%93%E3%82%99%E3%81%8F%E3%81%A1%E3%82%83%E3%82%93.mp3",
        name = "04 Hack You feat. うごくちゃん", artist = listOf(nekoHacker,hoge1))
    val tes4 = Track(url = "https://nekohacker.com/wp-content/uploads/2021/01/05-Daydream-feat.-mega-Sithu-Aye.mp3",
        name = "05 Daydream feat. mega & Sithu Aye", artist = listOf(nekoHacker,hoge2))
    val tes5 = Track(url = "https://nekohacker.com/wp-content/uploads/2021/01/06-Sweet-Dreams-feat.-%E5%88%A9%E9%A6%99.mp3",
        name = "06 Sweet Dreams feat. 利香", artist = listOf(nekoHacker,hoge2))
    val tes6 = Track(url = "https://nekohacker.com/wp-content/uploads/2021/01/07-Circulation.mp3",
        name = "07 Circulation", artist = listOf(nekoHacker,hoge2))

    val initTrack = Track(url = "", name = "", artist = null)
}