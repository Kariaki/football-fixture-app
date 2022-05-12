package com.thirdwinter.gomoneyassessment.ui.navigations.competition

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.kcoding.recyclerview_helper.SuperEntity
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.CompetitionStandingComponentBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionScorersBinding
import com.thirdwinter.gomoneyassessment.databinding.ScorerComponentBinding
import com.thirdwinter.gomoneyassessment.db.architecture.model.Scorer
import com.thirdwinter.gomoneyassessment.db.architecture.model.Table
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionScorers(val competitionId: Int) : Fragment(), SuperClickListener,
    GeneralAdapter.ViewHolderPlug {

    val viewModel: FootballViewModel by viewModels()
    var standingList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    lateinit var binding: FragmentCompetitionScorersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.insertCompetitionScorers(competitionId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompetitionScorersBinding.inflate(layoutInflater)
        generalAdapter.apply {
            superClickListener = this@CompetitionScorers
            viewHolderPlug = this@CompetitionScorers
        }

        binding.scorersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }

        viewModel.getScorersForCompetition(competitionId).observe(viewLifecycleOwner) {
            generalAdapter.items = it
            standingList = it
            generalAdapter.notifyDataSetChanged()
        }

        viewModel.loadState.observe(viewLifecycleOwner) {
        }

        return binding.root
    }


    override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(requireContext())
            .inflate(R.layout.scorer_component, group, false)
        return viewHolder(itemView)
    }

    override fun onClickItem(position: Int) {

    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = ScorerComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val scorer = types as Scorer
            val player = scorer.player
            binding.goals.text = scorer.numberOfGoals.toString()
            binding.name.text = player.name


        }
    }


}