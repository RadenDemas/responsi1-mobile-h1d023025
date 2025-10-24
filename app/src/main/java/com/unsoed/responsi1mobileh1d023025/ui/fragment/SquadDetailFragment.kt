package com.unsoed.responsi1mobileh1d023025.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unsoed.responsi1mobileh1d023025.databinding.FragmentSquadDetailBinding

class SquadDetailFragment(
    private val playerName: String?,
    private val position: String?,
    private val nationality: String?,
    private val dateOfBirth: String?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentSquadDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquadDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPlayerData()
    }

    private fun loadPlayerData() {
        binding.textViewPlayerName.text = playerName ?: "N/A"
        binding.textViewPlayerPosition.text = position ?: "N/A"
        binding.textViewPlayerNationality.text = nationality ?: "N/A"
        binding.textViewPlayerDateOfBirth.text = dateOfBirth ?: "N/A"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
