package com.thirdwinter.gomoneyassessment.db.architecture.repository

import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.model.*

class FakeFootballRepository:Repository {
    override suspend fun getAllCompetitions(): LiveData<List<Competition>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamSquad(teamId: Int): LiveData<List<Squad>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompetitionTable(competitionId: Int): LiveData<List<Table>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMatchesForToday(): LiveData<List<Table>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitions(onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTodaysMatch(onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionMatches(competitionId: Int, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionTeams(competitionId: Int, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionTables(competitionId: Int, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeamSquad(teamId: Int, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

}