package com.thirdwinter.gomoneyassessment.ui.navigations.competition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.thirdwinter.gomoneyassessment.R
import com.thirdwinter.gomoneyassessment.databinding.FragmentCompetitionBaseBinding
import com.thirdwinter.gomoneyassessment.ui.CompetitionPagerAdapter
import com.thirdwinter.gomoneyassessment.ui.navigations.TeamSquadInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionBaseFragment : Fragment(), TeamSquadInterface {

    lateinit var binding: FragmentCompetitionBaseBinding
    lateinit var pagerAdapter: CompetitionPagerAdapter
    val args: CompetitionBaseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCompetitionBaseBinding.inflate(layoutInflater)
        pagerAdapter = CompetitionPagerAdapter(requireActivity())

        binding.competitionPagers.adapter = pagerAdapter.apply {
            pages = listOf(
                CompetitionMatches(args.id),
                CompetitionStandings(args.id),
                CompetitionTeams(args.id).apply {
                    teamSquadInterface = this@CompetitionBaseFragment
                },
                CompetitionScorers(args.id)
            )
        }
        val mediator =
            TabLayoutMediator(binding.competitionTab, binding.competitionPagers) { tab, position ->
                when (position) {
                    0 ->
                        tab.text = "Matches"
                    1 ->
                        tab.text = "Standings"
                    2 ->
                        tab.text = "Teams"
                    3 ->
                        tab.text = "Scorers"

                }
            }

        mediator.attach()

        return binding.root
    }

    override fun onClickItem(teamId: Int) {
        findNavController().navigate(
            CompetitionBaseFragmentDirections.actionCompetitionBaseFragmentToTeamSquadModal(
                teamId
            )
        )
    }


}