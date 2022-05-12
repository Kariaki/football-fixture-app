package com.thirdwinter.gomoneyassessment.ui.navigations.modal

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.kcoding.recyclerview_helper.SuperEntity
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.CompetitionMatchComponentBinding
import com.thirdwinter.gomoneyassessment.databinding.FragmentTeamSquadModalBinding
import com.thirdwinter.gomoneyassessment.databinding.TeamSquadComponentBinding
import com.thirdwinter.gomoneyassessment.db.architecture.model.Match
import com.thirdwinter.gomoneyassessment.db.architecture.model.Squad
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import com.thirdwinter.gomoneyassessment.util.Constants.TEAM_ID
import com.thirdwinter.gomoneyassessment.util.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSquadModal : BottomSheetDialogFragment(), SuperClickListener,
    GeneralAdapter.ViewHolderPlug {


    lateinit var binding: FragmentTeamSquadModalBinding
    var competitionList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    val viewModel: FootballViewModel by viewModels()
    val args: TeamSquadModalArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.insertTeamSquad(args.teamId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamSquadModalBinding.inflate(layoutInflater)
        generalAdapter.apply {
            superClickListener = this@TeamSquadModal
            viewHolderPlug = this@TeamSquadModal
        }

        binding.squadRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }

        viewModel.getTeamSquad(args.teamId).observe(viewLifecycleOwner) {
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
            .inflate(R.layout.team_squad_component, group, false)
        return viewHolder(itemView)
    }

    override fun onClickItem(position: Int) {

    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = TeamSquadComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val squad = types as Squad
            binding.name.text = squad.name
            binding.position.text = squad.position


        }
    }

}