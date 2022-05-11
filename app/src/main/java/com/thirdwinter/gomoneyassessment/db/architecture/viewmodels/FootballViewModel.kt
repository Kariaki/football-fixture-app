package com.thirdwinter.gomoneyassessment.db.architecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Match
import com.thirdwinter.gomoneyassessment.db.architecture.repositories.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FootballViewModel @Inject constructor(private val repository: FootballRepository) :
    ViewModel() {

    val competitions: LiveData<List<Competition>> = repository.getCompetitions()

     suspend fun insertCompetitions(onFailure: () -> Unit) {

            repository.insertCompetitions(onFailure)

    }

    suspend fun getCompetitionTeam(){
        repository.insertTeam(2013)
    }
}