package ru.myitschool.work.ui.admin

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentAdminBinding
import ru.myitschool.work.entities.EmployeeEntity
import ru.myitschool.work.utils.collectWithLifecycle

class AdminFragment : Fragment(R.layout.fragment_admin) {
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminViewModel by viewModels{ AdminViewModel.Factory }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAdminBinding.bind(view)
        binding.searchBtn.setOnClickListener {
            viewModel.searchUser(
                binding.search.text.toString()
            )
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val search = binding.search.text
                val isEnabled =
                    search.length >= 3 && !search[0].isDigit() && search.matches(Regex("^[a-zA-Z0-9]*$"))
                binding.searchBtn.isEnabled = isEnabled
                if (isEnabled) {
                    binding.searchBtn.setBackgroundColor(resources.getColor(R.color.accent_color))
                    binding.searchBtn.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        ))
                } else {
                    binding.searchBtn.setBackgroundColor(resources.getColor(R.color.bg_color))
                    binding.searchBtn.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.secondary_text_color
                        ))
                }


            }
        }
        binding.search.addTextChangedListener(textWatcher)
        viewModel.infoState.collectWithLifecycle(this){ state ->
            when(state){
                is AdminViewModel.SearchState.Error -> {
                    binding.error.visibility = View.VISIBLE
                    binding.error.text = state.message

                }
                AdminViewModel.SearchState.Loading -> {
                    binding.error.visibility = View.GONE
                    binding
                }
                is AdminViewModel.SearchState.Success -> {
                    binding.error.visibility = View.GONE
                    showUserData(state.data)
                }
            }
        }
    }
    private fun showUserData(user: EmployeeEntity){
        binding.userName.text = user.name
        binding.position.text = user.position
    }
}