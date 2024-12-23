package uk.ac.tees.mad.artgallery.ui.homeScreen.model

import java.io.Serializable

data class Record(
    val culture: String,
    val dated: String,
    val department: String,
    val objectid: Int,
    val primaryimageurl: String,
    val title: String,
    val url: String,
    val classification: String,
    val division: String,
    val description: String,
    val labeltext: String,
    val provenance: String
):Serializable