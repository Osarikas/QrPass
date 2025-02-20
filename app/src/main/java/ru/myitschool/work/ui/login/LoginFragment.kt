package ru.myitschool.work.ui.login

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels{ LoginViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)

        val textWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun afterTextChanged(s: Editable?) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val username = binding.username.text
                val password = binding.password.text
                val isEnabled = username.length >= 3 && !username[0].isDigit() && username.matches(Regex("^[a-zA-Z0-9]*$")) &&
                        password.length >= 6
                binding.loginBtn.isEnabled = isEnabled
                if(isEnabled){
                    binding.loginBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.accent_color))
                    binding.loginBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }
                else{
                    binding.loginBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.bg_color))
                    binding.loginBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary_text_color))
                }

            }
        }
        binding.username.addTextChangedListener(textWatcher)
        binding.password.addTextChangedListener(textWatcher)

        binding.loginBtn.isEnabled = false
        binding.loginBtn.setOnClickListener{
            viewModel.login(binding.username.text.toString(), binding.password.text.toString())

        }
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    when(state){
                        is LoginViewModel.State.Error -> {
                            error.visibility = View.VISIBLE
                            error.text = state.message
                            loading.visibility = View.GONE
                            username.isEnabled = true

                        }
                        is LoginViewModel.State.Idle -> {
                            loading.visibility = View.GONE
                            error.visibility = View.GONE
                            username.isEnabled = true
                        }
                        is LoginViewModel.State.Loading -> {
                            loading.visibility = View.VISIBLE
                            error.visibility = View.GONE
                            username.isEnabled = false
                        }
                        is LoginViewModel.State.Success -> {
                            findNavController().navigate(R.id.mainFragment)
                        }
                    }

                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}