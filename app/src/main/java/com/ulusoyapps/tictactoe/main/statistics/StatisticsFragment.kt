package com.ulusoyapps.tictactoe.main.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ulusoyapps.tictactoe.databinding.FragmentStatisticsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_home.*

class StatisticsFragment : DaggerFragment() {

    private lateinit var binding: FragmentStatisticsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: StatisticsViewModel by viewModels { viewModelFactory }

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

        with(viewModel) {
            updateStatistics()
            statistics.observe(viewLifecycleOwner, Observer { statistics ->
                binding.statistics.updateStatistics(statistics)
            })
        }

        binding.resetStatistics.setOnClickListener {
            viewModel.resetStatistics()
        }
    }
}
