package com.thirdwinter.gomoneyassessment.db.di.hilt.module

import android.content.Context
import androidx.room.Room
import com.thirdwinter.gomoneyassessment.db.architecture.dao.api.FootballApi
import com.thirdwinter.gomoneyassessment.db.architecture.database.FootballDatabase
import com.thirdwinter.gomoneyassessment.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun retrofitInstance(): FootballApi = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FootballApi::class.java)

    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FootballDatabase::class.java,
        "database"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: FootballDatabase) = db.footballDao()

}


