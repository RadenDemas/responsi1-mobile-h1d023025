package com.unsoed.responsi1mobileh1d023025.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobileh1d023025.BuildConfig
import com.unsoed.responsi1mobileh1d023025.data.repository.TeamRepository
import com.unsoed.responsi1mobileh1d023025.databinding.FragmentTeamSquadBinding
import com.unsoed.responsi1mobileh1d023025.ui.adapter.TeamSquadAdapter
import com.unsoed.responsi1mobileh1d023025.view.view_model.MainViewModel
import com.unsoed.responsi1mobileh1d023025.view.view_model.MainViewModelFactory

class TeamSquadFragment : Fragment() {

    private var _binding: FragmentTeamSquadBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TeamRepository())
    }
    private lateinit var adapter: TeamSquadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TeamSquadAdapter { player ->
            showPlayerDetail(player.name, player.position, player.nationality, player.dateOfBirth)
        }
        binding.recyclerViewSquad.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSquad.adapter = adapter

        binding.progressBarSquad.visibility = View.VISIBLE
        binding.recyclerViewSquad.visibility = View.GONE
        binding.textViewTitle.visibility = View.GONE

        viewModel.team.observe(viewLifecycleOwner) { team ->
            binding.progressBarSquad.visibility = View.GONE

            if (team != null && team.squad.isNotEmpty()) {

                binding.recyclerViewSquad.visibility = View.VISIBLE
                binding.textViewTitle.visibility = View.VISIBLE
                adapter.setPlayers(team.squad)

                binding.textViewTitle.text = "${team.name ?: "Unknown"} Squad"
            } else {
                binding.recyclerViewSquad.visibility = View.GONE
                binding.textViewTitle.text = if (team == null) "Squad not found" else "${team.shortName} Squad (Empty)"
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.progressBarSquad.visibility = View.GONE
                binding.recyclerViewSquad.visibility = View.GONE
                binding.textViewTitle.visibility = View.GONE
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                Log.e("TeamSquadFragment", "Error fetching data: $it")
                binding.textViewTitle.text = "Error loading squad"
            }
        }
        viewModel.fetchTeamInfo(BuildConfig.FOOTBALL_API_KEY, 521)
    }

    private fun showPlayerDetail(name: String?, position: String?, nationality: String?, dateOfBirth: String?) {
        val detailFragment = SquadDetailFragment(
            playerName = name,
            position = position,
            nationality = nationality,
            dateOfBirth = dateOfBirth
        )
        detailFragment.show(parentFragmentManager, "SquadDetailFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

