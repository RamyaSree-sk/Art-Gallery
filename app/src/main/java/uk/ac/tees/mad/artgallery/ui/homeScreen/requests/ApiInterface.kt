package uk.ac.tees.mad.artgallery.ui.homeScreen.requests

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.ArtDetails

interface ApiInterface {

    @GET("object?")
    suspend fun getArt(
        @Query("apikey")apikey: String,
        @Query("page")page: Int
    ): ArtDetails
}