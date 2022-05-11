package com.thirdwinter.gomoneyassessment.db.architecture.repository

import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.dao.FootballDao
import com.thirdwinter.gomoneyassessment.db.architecture.dao.api.FootballApi
import com.thirdwinter.gomoneyassessment.db.architecture.model.*

class FootballRepository(private val api: FootballApi, private val dao: FootballDao) : Repository {

    override suspend fun getAllCompetitions(): LiveData<List<Competition>> = dao.getCompetitions()

    override suspend fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>>{

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
        val competitionResponse = api.getCompetitions()
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.competitions
            body?.forEach {
                dao.insertCompetition(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertTodaysMatch(onError: () -> Unit) {
        val competitionResponse = api.getTodayMatch()
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.matches
            body?.forEach {
                dao.insertMatch(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionMatches(competitionId: Int, onError: () -> Unit) {
        val competitionResponse = api.getMatchesForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.matches
            body?.forEach {

                it.competitionId=competitionId
                dao.insertMatch(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionTeams(competitionId: Int, onError: () -> Unit) {
        val competitionResponse = api.getTeamForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.teams
            body?.forEach {

                it.competitionId=competitionId
                dao.insertTeam(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionTables(competitionId: Int, onError: () -> Unit) {
        val competitionResponse = api.getTableForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.standings!!
            body?.forEach { standingResponse ->
                val tables = standingResponse.table!!
                tables.forEach {
                    it.competitionId=competitionId
                    dao.insertTable(it)
                }
            }
        } else {
            onError()
        }
    }

    override suspend fun insertTeamSquad(teamId: Int, onError: () -> Unit) {
        val competitionResponse = api.getTeamSquad(teamId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.squad
            body?.forEach {
                it.teamId=teamId
                dao.insertSquad(it)
            }
        } else {
            onError()
        }
    }


}