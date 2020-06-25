package com.ulusoyapps.tictactoe.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        binding.appNameText.text = getStyledAppName(requireContext())
        binding.root.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }
}
