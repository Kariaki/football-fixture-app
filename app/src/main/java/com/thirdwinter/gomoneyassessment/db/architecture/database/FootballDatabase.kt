package com.thirdwinter.gomoneyassessment.db.architecture.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thirdwinter.gomoneyassessment.db.architecture.dao.FootballDao
import com.thirdwinter.gomoneyassessment.db.architecture.database.converters.*
import com.thirdwinter.gomoneyassessment.db.architecture.model.*

//TODO DATABASE TABLES OR ENTITIES
@Database(
    entities = [Competition::class, Match::class, Team::class, Squad::class,Scorer::class,Table::class],
    exportSchema = false,
    version = 5
)
@TypeConverters(
    CompetitionConverter::class,
    ScoreConverter::class,
    TeamConverter::class,
    DateConverter::class,
    PlayerConverter::class,
    RefereeConverter::class
)
abstract class FootballDatabase : RoomDatabase() {


    abstract fun footballDao(): FootballDao

}