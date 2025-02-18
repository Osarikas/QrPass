package ru.myitschool.work.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
                binding.loginBtn.isEnabled = username.length >= 3 && !username[0].isDigit() && username.matches(Regex("^[a-zA-Z0-9]*$"))

            }
        }
        binding.username.addTextChangedListener(textWatcher)
        binding.loginBtn.isEnabled = false
        binding.loginBtn.setOnClickListener{
            viewModel.login(binding.username.text.toString())

        }
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    error.visibility = if (state is LoginViewModel.State.Error) View.VISIBLE else View.GONE
                    username.isEnabled = state !is LoginViewModel.State.Loading

                    if (state is LoginViewModel.State.Success) {
                        findNavController().navigate(R.id.mainFragment)
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