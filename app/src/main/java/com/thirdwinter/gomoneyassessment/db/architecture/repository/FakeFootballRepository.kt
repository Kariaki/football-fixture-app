package com.thirdwinter.gomoneyassessment.db.architecture.repository

import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.model.*
import java.util.*

class FakeFootballRepository:Repository {

    //TODO FOR UNIT TEST

    override fun getAllCompetitions(): LiveData<List<Competition>> {
        TODO("Not yet implemented")
    }

    override fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>> {
        TODO("Not yet implemented")
    }

    override fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>> {
        TODO("Not yet implemented")
    }

    override fun getTeamSquad(teamId: Int): LiveData<List<Squad>> {
        TODO("Not yet implemented")
    }

    override fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>> {
        TODO("Not yet implemented")
    }

    override fun getCompetitionTable(competitionId: Int): LiveData<List<Table>> {
        TODO("Not yet implemented")
    }

    override fun getMatchesForToday(date: String): LiveData<List<Match>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitions(onSuccess: () -> Unit, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTodaysMatch(onSuccess: () -> Unit, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionMatches(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionTeams(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionTables(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompetitionScorers(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeamSquad(teamId: Int, onSuccess: () -> Unit, onError: () -> Unit) {
        TODO("Not yet implemented")
    }

}