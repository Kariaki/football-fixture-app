package com.thirdwinter.gomoneyassessment.db.architecture.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thirdwinter.gomoneyassessment.db.architecture.model.*
import java.util.*

@Dao
interface FootballDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompetition(competition: Competition)

    @Query("select * from competition")
    fun getCompetitions(): LiveData<List<Competition>>

    @Query("select * from `match` where date like:date order by time desc")
    fun getTodaysMatch(date: String): LiveData<List<Match>>

    @Query("select * from competition where id like:competitionId")
    fun getCompetitionById(competitionId: Int): LiveData<List<Competition>>

    @Query("select * from team where id like:teamId")
    fun getTeamById(teamId: Int): LiveData<List<Team>>

    @Query("select * from `match` where competitionId like:competitionId")
    fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>>

    @Query("select * from team where competitionId like:competitionId")
    fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>>

    @Query("select * from `table` where competitionId like:competitionId order by position asc")
    fun getCompetitionTable(competitionId: Int): LiveData<List<Table>>

    @Query("select * from `scorers` where competitionId like:competitionId")
    fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>>

    @Query("select * from `squad` where teamId like:teamId")
    fun getTeamSquad(teamId: Int): LiveData<List<Squad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: Match)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSquad(squad: Squad)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTable(table: Table)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScorer(scorer: Scorer)


}