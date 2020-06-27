package com.ulusoyapps.tictactoe.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ulusoyapps.tictactoe.R
import com.ulusoyapps.tictactoe.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment

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
        binding.topButton.run {
            text = getString(R.string.start_game)
            setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
            }
        }
        binding.bottomButton.run {
            text = getString(R.string.game_statistics)
            setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStatisticsFragment())
            }
        }
    }
}
