package com.thirdwinter.gomoneyassessment.ui.navigations

import android.content.Context
import android.os.Bundle
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
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionsBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentTodaysMatchBinding
import com.thirdwinter.gomoneyassessment.databinding.TodaysMatchComponentBinding
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Match
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import com.thirdwinter.gomoneyassessment.util.DateTimeUtils
import com.thirdwinter.gomoneyassessment.util.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodaysMatchFragment : Fragment(), SuperClickListener, GeneralAdapter.ViewHolderPlug {

    var competitionList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    val viewModel: FootballViewModel by viewModels()

    lateinit var binding: FragmentTodaysMatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.insertMatchesForToday()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodaysMatchBinding.inflate(layoutInflater)

        generalAdapter.apply {
            superClickListener = this@TodaysMatchFragment
            viewHolderPlug = this@TodaysMatchFragment
        }

        binding.matchRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }
        val todaysDate = DateTimeUtils.getDate(System.currentTimeMillis())

        viewModel.getMatchesForToday(todaysDate).observe(viewLifecycleOwner) {
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
            .inflate(R.layout.todays_match_component, group, false)

        return viewHolder(itemView)
    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = TodaysMatchComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val match = types as Match
            val homeTeam = match.homeTeam
            val awayTeam = match.awayTeam
            val competition = match.competition!!
            binding.competitionName.text = competition.name
            binding.team1Name.text = homeTeam?.name
            // Glide.with(context).load(homeTeam?.crestUrl).placeholder(R.drawable.ic_launcher_background).into(binding.team1image)

            binding.team2Name.text = awayTeam?.name
            //  Glide.with(context).load(awayTeam?.crestUrl).placeholder(R.drawable.ic_launcher_background).into(binding.team2Image)

        }
    }

    override fun onClickItem(position: Int) {

    }

}