package uk.ac.tees.mad.artgallery.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [LocalRecord::class],
    version = 1
)
abstract class RecordDatabase: RoomDatabase() {
    abstract fun dao(): RecordDao
}