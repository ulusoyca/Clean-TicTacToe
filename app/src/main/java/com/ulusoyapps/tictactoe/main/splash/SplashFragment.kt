package com.ulusoyapps.tictactoe.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulusoyapps.tictactoe.databinding.FragmentSplashBinding
import com.ulusoyapps.tictactoe.main.extensions.getStyledAppName
import dagger.android.support.DaggerFragment

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
        val spannableAppName = getStyledAppName(requireContext())
        binding.logoContainer.appNameText.text = spannableAppName
    }
}
