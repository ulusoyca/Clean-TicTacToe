package com.ulusoyapps.tictactoe.main.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ulusoyapps.tictactoe.databinding.FragmentSplashBinding
import dagger.android.support.DaggerFragment

private const val START_DELAY = 2000L

class SplashFragment : DaggerFragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed(
            { startHomeFragment() }, START_DELAY
        )
    }

    private fun startHomeFragment() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }
}
