package com.tiprecorder.di

import android.content.Context
import com.tiprecorder.data.dao.TipDao
import com.tiprecorder.data.database.TipDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTipDatabase(@ApplicationContext context: Context): TipDatabase {
        return TipDatabase.getDatabase(context)
    }

    @Provides
    fun provideTipDao(database: TipDatabase): TipDao {
        return database.tipDao()
    }
}
