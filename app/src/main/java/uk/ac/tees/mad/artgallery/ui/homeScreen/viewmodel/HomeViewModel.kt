package uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uk.ac.tees.mad.artgallery.roomdb.RecordDatabase
import uk.ac.tees.mad.artgallery.roomdb.toLocalRecord
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.ArtDetails
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.homeScreen.requests.RetrofitInstance

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val API_KEY = "0990aa20-8197-440d-ae3f-df69bd11c091"

    private val rdb = Room.databaseBuilder(
        application,
        RecordDatabase::class.java, "record.db"
    ).build()

    private val recordDao = rdb.dao()

    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    private val _artGallery = MutableStateFlow<ArtDetails?>(null)
    val artGallery = _artGallery.asStateFlow()

    private val _artDetail = MutableStateFlow<List<Record>>(emptyList())
    val artDetail = _artDetail.asStateFlow()

    private val _pagenum = MutableStateFlow(1)
    val pagenum = _pagenum.asStateFlow()

    private var isLoading = false


    //Searching in the arts gallery.
    private val _isSearching = MutableStateFlow<Boolean>(false)
    val isSearching = _isSearching.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    val searchResult: StateFlow<List<Record>> =
        combine(_artDetail, _text){arts, text->
            if (text.isBlank()){
                arts
            }else{
                arts.filter {
                    it.division.contains(text, ignoreCase = true) ||
                            it.department.contains(text, ignoreCase = true)
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        fetchRecord()
    }

    fun fetchRecord(){

        if (isLoading || pagenum.value>(_artGallery.value?.info?.pages ?: 1)) return

        isLoading = true
        viewModelScope.launch {
            try {
                val allRecords = RetrofitInstance.api.getArt(API_KEY, pagenum.value)
                _artGallery.value = allRecords

                val recordEntities = allRecords.records.map{
                    it.toLocalRecord()
                }
                _artDetail.value = _artDetail.value + allRecords.records

                recordDao.addRecord(recordEntities)

                val insertedData = recordDao.getAllRecords()

                _pagenum.value += 1
                Log.i(
                    "Database insertion: ",
                    insertedData.toString()
                )
            }catch (e: Exception){
                Log.i("Error", "Cannot fetch the art Details from the api")
            }
            isLoading=false
        }
    }

    fun updateSearchQuery(query: String){
        _text.value = query
    }

    fun togggleSearch(){
        _isSearching.value = !_isSearching.value
        if(!_isSearching.value){
            updateSearchQuery("")
        }
    }

    //Theme toggling.
    fun defaultTheme(q: Boolean){
        if (q){
            _darkTheme.value = true
        }
    }

    fun changeTheme(){
        _darkTheme.value = !_darkTheme.value!!
    }

}