package com.thirdwinter.gomoneyassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.thirdwinter.gomoneyassessment.db.architecture.dao.api.FootballApi
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val viewModel: FootballViewModel by viewModels()

    @Inject
    lateinit var api: FootballApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        lifecycleScope.launchWhenStarted {
            viewModel.insertCompetitions {
                runBlocking {
                    Log.d(TAG, "an error occured")

                }
            }
        }

         */

        /*
        lifecycleScope.launchWhenStarted {
            val response = api.getTableForCompetition(2013)
            if (response.isSuccessful) {
                val body = response.body()!!
                val table = body.standings!!
               val competition= body.competition.id
                table.get(0).table
                    ?.forEach {
                        it.apply {
                            competitionId=competition
                            team=team.apply { competitionId=competition }
                        }

                        Log.d(TAG,it.toString())
                        Log.d(TAG,"/n")

                    }

            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        }


         */

        lifecycleScope.launchWhenStarted {

            viewModel.getCompetitionTeam()
        }



    }
}