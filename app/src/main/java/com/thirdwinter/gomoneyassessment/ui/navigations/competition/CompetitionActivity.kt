package com.thirdwinter.gomoneyassessment.ui.navigations.competition

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.ActivityCompetitionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompetitionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompetitionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}