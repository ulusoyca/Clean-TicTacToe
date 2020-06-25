package com.ulusoyapps.tictactoe.main.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulusoyapps.tictactoe.databinding.FragmentStatisticsBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*

class StatisticsFragment : DaggerFragment() {

    private lateinit var binding: FragmentStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
