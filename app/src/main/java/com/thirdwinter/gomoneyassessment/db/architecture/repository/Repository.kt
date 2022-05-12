package com.thirdwinter.gomoneyassessment.db.architecture.repository

import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.model.*
import java.util.*

interface Repository {

    fun getAllCompetitions(): LiveData<List<Competition>>

    fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>>
    fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>>
    fun getTeamSquad(teamId: Int): LiveData<List<Squad>>
    fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>>
    fun getCompetitionTable(competitionId: Int): LiveData<List<Table>>
    fun getMatchesForToday(date: String): LiveData<List<Match>>

    suspend fun insertCompetitions(onSuccess: () -> Unit, onError: () -> Unit)

    suspend fun insertTodaysMatch(onSuccess: () -> Unit, onError: () -> Unit)

    suspend fun insertCompetitionMatches(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    suspend fun insertCompetitionTeams(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    suspend fun insertCompetitionTables(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    suspend fun insertCompetitionScorers(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    suspend fun insertTeamSquad(teamId: Int, onSuccess: () -> Unit, onError: () -> Unit)



}