package com.thirdwinter.gomoneyassessment.db.architecture.repository

import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.model.*

interface Repository {

    suspend fun getAllCompetitions(): LiveData<List<Competition>>

    suspend fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>>
    suspend fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>>
    suspend fun getTeamSquad(teamId: Int): LiveData<List<Squad>>
    suspend fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>>
    suspend fun getCompetitionTable(competitionId: Int): LiveData<List<Table>>
    suspend fun getMatchesForToday(): LiveData<List<Table>>


    suspend fun insertCompetitions(onError: () -> Unit)

    suspend fun insertTodaysMatch(onError: () -> Unit)

    suspend fun insertCompetitionMatches(competitionId: Int, onError: () -> Unit)

    suspend fun insertCompetitionTeams(competitionId: Int, onError: () -> Unit)

    suspend fun insertCompetitionTables(competitionId: Int, onError: () -> Unit)
    suspend fun insertTeamSquad(teamId: Int, onError: () -> Unit)

}