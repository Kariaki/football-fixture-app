package com.thirdwinter.gomoneyassessment.db.architecture.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thirdwinter.gomoneyassessment.db.architecture.model.*

@Dao
interface FootballDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompetition(competition: Competition)

    @Query("select * from competition")
    fun getCompetitions():LiveData<List<Competition>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMatch(match: Match)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTeam(team: Team)

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertSquad(squad: Squad)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTable(table: Table)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScorer(scorer: Scorer)

}