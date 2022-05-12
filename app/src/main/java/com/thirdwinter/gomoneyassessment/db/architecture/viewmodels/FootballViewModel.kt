package com.thirdwinter.gomoneyassessment.db.architecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirdwinter.gomoneyassessment.db.architecture.model.*
import com.thirdwinter.gomoneyassessment.db.architecture.repository.FootballRepository
import com.thirdwinter.gomoneyassessment.db.architecture.repository.Repository
import com.thirdwinter.gomoneyassessment.util.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FootballViewModel @Inject constructor(@Named("datasource") private val repository: Repository) :
    ViewModel() {

    private val _loadState: MutableLiveData<LoadState> = MutableLiveData(LoadState.LOADING)
    val loadState: LiveData<LoadState> = _loadState


    fun getAllCompetitions(): LiveData<List<Competition>> {

        return repository.getAllCompetitions()
    }

    fun getMatchesForToday(date: String): LiveData<List<Match>> {
        _loadState.postValue(LoadState.DONE)
        return repository.getMatchesForToday(date)
    }

    suspend fun insertCompetitions() {

        repository.insertCompetitions({
            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }

    }

    suspend fun insertMatchesForToday() {

        repository.insertTodaysMatch({

            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }

    }

    suspend fun insertTeamSquad(teamId: Int) {
        repository.insertTeamSquad(teamId, {

            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }
    }

    suspend fun insertCompetitionTable(competitionId: Int) {
        repository.insertCompetitionTables(competitionId, {

            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }
    }

    suspend fun insertCompetitionTeams(competitionId: Int) {
        repository.insertCompetitionTeams(competitionId, {

            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }
    }

    suspend fun insertCompetitionMatches(competitionId: Int) {
        repository.insertCompetitionMatches(competitionId, {

            _loadState.postValue(LoadState.DONE)
        }) {
            _loadState.postValue(LoadState.ERROR)
        }
    }
    suspend fun insertCompetitionScorers(competitionId: Int){
        repository.insertCompetitionScorers(competitionId,{

            _loadState.postValue(LoadState.DONE)
        }){

            _loadState.postValue(LoadState.ERROR)
        }
    }

    fun getMatchesForCompetition(competitionId: Int): LiveData<List<Match>> =
        repository.getCompetitionMatches(competitionId)

    fun getTablesForCompetition(competitionId: Int): LiveData<List<Table>> =
        repository.getCompetitionTable(competitionId)

    fun getScorersForCompetition(competitionId: Int): LiveData<List<Scorer>> =
        repository.getCompetitionScorers(competitionId)

    fun getTeamsForCompetition(competitionId: Int): LiveData<List<Team>> =
        repository.getCompetitionTeams(competitionId)

    fun getTeamSquad(teamId: Int): LiveData<List<Squad>> = repository.getTeamSquad(teamId)


}