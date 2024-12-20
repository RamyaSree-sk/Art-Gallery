package uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.ArtDetails
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.homeScreen.requests.RetrofitInstance

class HomeViewModel: ViewModel() {

    private val API_KEY = "0990aa20-8197-440d-ae3f-df69bd11c091"

    private val _artGallery = MutableStateFlow<ArtDetails?>(null)
    private val artGallery = _artGallery.asStateFlow()

    private val _artDetail = MutableStateFlow<List<Record>>(emptyList())
    val artDetail = _artDetail.asStateFlow()

    init {
        fetchRecord()
    }

    fun fetchRecord(){
        viewModelScope.launch {
            try {
                val allRecords = RetrofitInstance.api.getArt(API_KEY)
                _artGallery.value = allRecords
                _artDetail.value = allRecords.records
                Log.i("Data Obtained: ", "The call is successfull and data is: ${_artDetail.value}")
            }catch (e: Exception){
                Log.i("Error", "Cannot fetch the art Details from the api")
            }
        }
    }

}