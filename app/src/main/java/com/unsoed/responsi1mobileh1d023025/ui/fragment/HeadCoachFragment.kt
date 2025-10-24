package com.unsoed.responsi1mobileh1d023025.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.unsoed.responsi1mobileh1d023025.BuildConfig
import com.unsoed.responsi1mobileh1d023025.data.repository.TeamRepository
import com.unsoed.responsi1mobileh1d023025.databinding.FragmentHeadCoachBinding
import com.unsoed.responsi1mobileh1d023025.view.view_model.MainViewModel
import com.unsoed.responsi1mobileh1d023025.view.view_model.MainViewModelFactory

class HeadCoachFragment : Fragment() {

    private var _binding: FragmentHeadCoachBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TeamRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadCoachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentLayoutCoach.visibility = View.GONE
        binding.progressBarCoach.visibility = View.VISIBLE

        setupObservers()

        viewModel.fetchTeamInfo(BuildConfig.FOOTBALL_API_KEY, 521)
    }

    private fun setupObservers() {
        viewModel.team.observe(viewLifecycleOwner) { team ->
            binding.progressBarCoach.visibility = View.GONE
            binding.contentLayoutCoach.visibility = View.VISIBLE

            if (team != null) {
                val coach = team.coach
                binding.textViewCoachName.text = coach?.name ?: "N/A"
                binding.textViewCoachNationality.text = coach?.nationality ?: "N/A"
                binding.textViewCoachBirthDate.text = coach?.dateOfBirth ?: "N/A"
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.progressBarCoach.visibility = View.GONE
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}