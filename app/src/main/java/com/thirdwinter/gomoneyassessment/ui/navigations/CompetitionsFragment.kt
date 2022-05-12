package com.thirdwinter.gomoneyassessment.ui.navigations

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.viewmodels.FootballViewModel
import com.thirdwinter.gomoneyassessment.util.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsFragment : Fragment(), SuperClickListener, GeneralAdapter.ViewHolderPlug {


    lateinit var binding: FragmentCompetitionsBinding

    var competitionList: List<SuperEntity> = listOf()
    val generalAdapter = GeneralAdapter()
    val viewModel: FootballViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.insertCompetitions()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCompetitionsBinding.inflate(layoutInflater)

        generalAdapter.apply {
            superClickListener = this@CompetitionsFragment
            viewHolderPlug = this@CompetitionsFragment
        }

        binding.competitionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = generalAdapter
        }

        viewModel.getAllCompetitions().observe(viewLifecycleOwner) {
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
            .inflate(R.layout.competition_component, group, false)
        return viewHolder(itemView)
    }

    fun viewHolder(view: View): MainViewHolder = object : MainViewHolder(view) {
        val binding = CompetitionComponentBinding.bind(view)
        override fun bindPostType(
            types: SuperEntity,
            context: Context,
            clickListener: SuperClickListener
        ) {
            val competition = types as Competition
            binding.name.text = competition.name
            Glide.with(context).load(competition.emblemUrl)
                .placeholder(R.drawable.ic_launcher_background).into(binding.emblemUrl)

            binding.root.setOnClickListener {
                clickListener.onClickItem(layoutPosition)
            }

        }
    }


    override fun onClickItem(position: Int) {

        val competition = competitionList[position] as Competition
        findNavController().navigate(
            CompetitionsFragmentDirections.actionCompetitionsFragmentToCompetitionBaseFragment(
                competition.id,
                competition.name!!
            )
        )

    }


}