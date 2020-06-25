package com.ulusoyapps.tictactoe.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ulusoyapps.tictactoe.databinding.FragmentHomeBinding
import com.ulusoyapps.tictactoe.main.extensions.getStyledAppName
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : DaggerFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            appNameText.text = getStyledAppName(requireContext())
            startGame.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
            }
            statistics.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStatisticsFragment())
            }
        }
    }
}
