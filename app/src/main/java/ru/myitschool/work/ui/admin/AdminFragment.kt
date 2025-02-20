package ru.myitschool.work.ui.admin

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentAdminBinding
import ru.myitschool.work.entities.EmployeeEntity
import ru.myitschool.work.utils.buttonRecolor
import ru.myitschool.work.utils.collectWithLifecycle


class AdminFragment : Fragment(R.layout.fragment_admin) {
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminViewModel by viewModels{ AdminViewModel.Factory }
    private var currentBtnState: Boolean = false
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
                @Suppress("DEPRECATION")
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
                    binding.userInfo.visibility = View.GONE

                }
                AdminViewModel.SearchState.Loading -> {
                    binding.error.visibility = View.GONE
                    binding.userInfo.visibility = View.GONE
                }
                is AdminViewModel.SearchState.Success -> {
                    binding.error.visibility = View.GONE
                    binding.userInfo.visibility = View.VISIBLE
                    showUserData(state.data)
                }
            }
        }
        binding.blockBtn.setOnClickListener {
            viewModel.changeState(binding.search.text.toString())

        }
        viewModel.blockState.collectWithLifecycle(this){ state ->
            when(state){
                is AdminViewModel.BlockState.Error -> {
                    binding.error.visibility = View.VISIBLE
                    binding.error.text = state.message
                }
                AdminViewModel.BlockState.Loading -> {
                    binding.error.visibility = View.GONE
                    binding.userInfo.visibility = View.GONE
                }
                AdminViewModel.BlockState.Success -> {
                    binding.error.visibility = View.GONE
                    binding.userInfo.visibility = View.VISIBLE
                    currentBtnState = !currentBtnState
                    btnState(currentBtnState)

                }
            }
        }

    }
    private fun btnState(bool : Boolean){
        currentBtnState = bool
        if(bool){
            binding.blockBtn.text = ContextCompat.getString(requireContext(), R.string.block_btn)
            buttonRecolor(requireContext(), binding.blockBtn, R.color.accent_color, R.color.white )
        }
        else{
            binding.blockBtn.text = ContextCompat.getString(requireContext(), R.string.unblock_btn)
            buttonRecolor(requireContext(), binding.blockBtn, R.color.bg_color, R.color.secondary_text_color )
        }
    }
    private fun showUserData(user: EmployeeEntity){
        binding.userName.text = user.name
        binding.position.text = user.position
        btnState(user.qrEnabled)
        Picasso.get().load(user.photoUrl).into(binding.avatar)

    }

}