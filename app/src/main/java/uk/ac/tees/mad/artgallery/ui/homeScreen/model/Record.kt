package uk.ac.tees.mad.artgallery.ui.homeScreen.model

import uk.ac.tees.mad.artgallery.roomdb.LocalRecord
import java.io.Serializable

data class Record(
    val culture: String,
    val dated: String,
    val department: String,
    val primaryimageurl: String,
    val title: String,
    val url: String,
    val classification: String,
    val division: String,
    val description: String,
    val labeltext: String,
    val provenance: String,
    val objectid: Int
):Serializable

fun LocalRecord.toRecord(): Record {
    return Record(
        culture = this.culture ?: "",
        dated = this.dated ?: "",
        department = this.department ?: "",
        primaryimageurl = this.primaryimageurl ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        classification = this.classification ?: "",
        division = this.division ?: "",
        description = this.description ?: "",
        labeltext = this.labeltext ?: "",
        provenance = this.provenance ?: "",
        objectid = this.objectid,
    )
}