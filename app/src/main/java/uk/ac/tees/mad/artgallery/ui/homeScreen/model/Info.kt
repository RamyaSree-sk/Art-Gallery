package uk.ac.tees.mad.artgallery.ui.homeScreen.model

data class Info(
    val next: String,
    val page: Int,
    val pages: Int,
    val responsetime: String,
    val totalrecords: Int,
    val totalrecordsperquery: Int
)