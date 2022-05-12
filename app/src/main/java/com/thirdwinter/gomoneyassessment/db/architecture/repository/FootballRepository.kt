package com.thirdwinter.gomoneyassessment.db.architecture.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.thirdwinter.gomoneyassessment.db.architecture.dao.FootballDao
import com.thirdwinter.gomoneyassessment.db.architecture.dao.api.FootballApi
import com.thirdwinter.gomoneyassessment.db.architecture.model.*
import com.thirdwinter.gomoneyassessment.util.DateTimeUtils
import java.util.*

class FootballRepository(private val api: FootballApi, private val dao: FootballDao) : Repository {

    override fun getAllCompetitions(): LiveData<List<Competition>> = dao.getCompetitions()

    override fun getCompetitionMatches(competitionId: Int): LiveData<List<Match>> =
        dao.getCompetitionMatches(competitionId)

    override fun getCompetitionTeams(competitionId: Int): LiveData<List<Team>> =
        dao.getCompetitionTeams(competitionId)

    override fun getTeamSquad(teamId: Int): LiveData<List<Squad>> = dao.getTeamSquad(teamId)

    override fun getCompetitionScorers(competitionId: Int): LiveData<List<Scorer>> =
        dao.getCompetitionScorers(competitionId)


    override fun getCompetitionTable(competitionId: Int): LiveData<List<Table>> =
        dao.getCompetitionTable(competitionId)

    override fun getMatchesForToday(date: String): LiveData<List<Match>> = dao.getTodaysMatch(date)

    override suspend fun insertCompetitions(onSuccess: () -> Unit, onError: () -> Unit) {
        val competitionResponse = api.getCompetitions()
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.competitions
            onSuccess()
            body?.forEach {
                dao.insertCompetition(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertTodaysMatch(onSuccess: () -> Unit, onError: () -> Unit) {
        val competitionResponse = api.getTodayMatch()
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.matches
            onSuccess()
            body?.forEach {
                it.time = DateTimeUtils.getTime(it.utcDate!!)
                it.date = DateTimeUtils.getDate(it.utcDate)
                dao.insertMatch(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionMatches(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val competitionResponse = api.getMatchesForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.matches
            onSuccess()
            body?.forEach {

                it.competitionId = competitionId

                dao.insertMatch(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionTeams(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val competitionResponse = api.getTeamForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.teams
            onSuccess()
            body?.forEach {

                it.competitionId = competitionId
                dao.insertTeam(it)
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionTables(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val competitionResponse = api.getTableForCompetition(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.standings!!
            onSuccess()
            body?.forEach { standingResponse ->
                val tables = standingResponse.table!!
                tables.forEach {
                    it.competitionId = competitionId
                    dao.insertTable(it)
                }
            }
        } else {
            onError()
        }
    }

    override suspend fun insertCompetitionScorers(
        competitionId: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val competitionResponse = api.getCompetitionScorers(competitionId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.scorers!!
            onSuccess()
            body.forEach {
                it.competitionId = competitionId
                dao.insertScorer(it)
            }

        } else {
            onError()
        }
    }

    override suspend fun insertTeamSquad(teamId: Int, onSuccess: () -> Unit, onError: () -> Unit) {
        val competitionResponse = api.getTeamSquad(teamId)
        if (competitionResponse.isSuccessful) {
            val body = competitionResponse.body()?.squad
            onSuccess()
            body?.forEach {
                it.teamId = teamId
                dao.insertSquad(it)
            }
        } else {
            onError()
        }
    }


}