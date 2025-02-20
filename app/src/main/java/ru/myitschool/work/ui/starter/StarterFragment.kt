package ru.myitschool.work.ui.starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentStarterBinding

class StarterFragment : Fragment(R.layout.fragment_starter) {
    private var _binding: FragmentStarterBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentStarterBinding.bind(view)
        binding.loginBtn.setOnClickListener { findNavController().navigate(R.id.loginFragment) }
    }
}