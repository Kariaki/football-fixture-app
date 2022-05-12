package com.thirdwinter.gomoneyassessment.ui.navigations.competition

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.google.android.material.snackbar.Snackbar
import com.kcoding.recyclerview_helper.SuperEntity
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.CompetitionComponentBinding
import com.thirdwinter.gomoneyassessment.databinding.CompetitionMatchComponentBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionMatchesBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionsBinding
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Match
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import com.thirdwinter.gomoneyassessment.util.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionMatches(val competitionId: Int) : Fragment(), SuperClickListener,
    GeneralAdapter.ViewHolderPlug {


    lateinit var binding: FragmentCompetitionMatchesBinding
    var competitionList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    val viewModel: FootballViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.insertCompetitionMatches(competitionId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCompetitionMatchesBinding.inflate(layoutInflater)
        generalAdapter.apply {
            superClickListener = this@CompetitionMatches
            viewHolderPlug = this@CompetitionMatches
        }

        binding.matchesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }

        viewModel.getMatchesForCompetition(competitionId).observe(viewLifecycleOwner) {
            generalAdapter.items = it
            competitionList = it

            generalAdapter.notifyDataSetChanged()
        }

        viewModel.loadState.observe(viewLifecycleOwner) {
            when (it) {
                LoadState.DONE -> binding.loader.visibility = View.GONE
                LoadState.LOADING -> binding.loader.visibility = View.VISIBLE
                LoadState.ERROR -> {
                    binding.loader.visibility = View.GONE
                    Snackbar.make(binding.root, "unable to reach server", Snackbar.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    binding.loader.visibility = View.VISIBLE
                }
            }
        }

        return binding.root
    }

    override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(requireContext())
            .inflate(R.layout.competition_match_component, group, false)
        return viewHolder(itemView)
    }

    override fun onClickItem(position: Int) {

    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = CompetitionMatchComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val match = types as Match
            val homeTeam = match.homeTeam
            val awayTeam = match.awayTeam
            binding.team1Name.text = homeTeam?.name

            binding.team2Name.text = awayTeam?.name


        }
    }


}