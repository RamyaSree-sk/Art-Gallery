package uk.ac.tees.mad.artgallery.ui.homeScreen.model

data class Record(
    val culture: String,
    val dated: String,
    val department: String,
    val objectid: Int,
    val primaryimageurl: String,
    val title: String,
    val url: String,
    val classification: String,
    val division: String
)