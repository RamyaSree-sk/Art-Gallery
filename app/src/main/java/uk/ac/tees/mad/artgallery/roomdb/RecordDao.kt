package uk.ac.tees.mad.artgallery.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(record: List<LocalRecord>)

    @Query("SELECT * FROM localrecord")
    suspend fun getAllRecords(): List<LocalRecord>

}