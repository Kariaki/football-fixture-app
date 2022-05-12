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
import com.bumptech.glide.Glide
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.kcoding.recyclerview_helper.SuperEntity
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.CompetitionTeamComponentBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionTeamsBinding
import com.thirdwinter.gomoneyassessment.db.architecture.model.Team
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import com.thirdwinter.gomoneyassessment.ui.navigations.TeamSquadInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionTeams(val competitionId: Int) : Fragment(), SuperClickListener,
    GeneralAdapter.ViewHolderPlug {

    private val viewModel: FootballViewModel by viewModels()
    var teamList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    lateinit var binding: FragmentCompetitionTeamsBinding

    lateinit var teamSquadInterface: TeamSquadInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.insertCompetitionTeams(competitionId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCompetitionTeamsBinding.inflate(layoutInflater)
        generalAdapter.apply {
            superClickListener = this@CompetitionTeams
            viewHolderPlug = this@CompetitionTeams
        }

        binding.teamRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }

        viewModel.getTeamsForCompetition(competitionId).observe(viewLifecycleOwner) {
            generalAdapter.items = it
            teamList = it
            generalAdapter.notifyDataSetChanged()

        }


        viewModel.loadState.observe(viewLifecycleOwner) {
        }

        return binding.root
    }


    override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(requireContext())
            .inflate(R.layout.competition_team_component, group, false)
        return viewHolder(itemView)
    }

    override fun onClickItem(position: Int) {
        val clickedTeam = teamList[position] as Team
        teamSquadInterface.onClickItem(clickedTeam.id)
    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = CompetitionTeamComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val team = types as Team

            Glide.with(context).load(
                team
                    .crestUrl
            ).placeholder(R.drawable.ic_launcher_background).into(binding.crestUrl)
            binding.name.text = team.name

            binding.root.setOnClickListener {
                clickListener.onClickItem(layoutPosition)
            }
        }

    }
}