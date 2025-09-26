package com.tiprecorder.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.tiprecorder.data.converter.DateConverter
import com.tiprecorder.data.dao.TipDao
import com.tiprecorder.data.model.Tip

@Database(
    entities = [Tip::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TipDatabase : RoomDatabase() {
    abstract fun tipDao(): TipDao

    companion object {
        @Volatile
        private var INSTANCE: TipDatabase? = null

        fun getDatabase(context: Context): TipDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TipDatabase::class.java,
                    "tip_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
