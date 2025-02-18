package ru.myitschool.work.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentMainBinding
import ru.myitschool.work.domain.entities.UserEntity
import ru.myitschool.work.ui.qr.scan.QrScanDestination
import ru.myitschool.work.utils.UserState
import ru.myitschool.work.utils.collectWhenStarted

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{ MainViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        viewModel.getUserData()
        binding.refresh.setOnClickListener { viewModel.getUserData() }
        binding.logout.setOnClickListener { logout() }
        binding.scan.setOnClickListener { onScanClick() }
        viewModel.userState.collectWhenStarted(this) { state ->
            when (state) {
                is UserState.Error -> {
                    showError()
                    binding.loading.visibility = View.GONE
                    binding.refresh.visibility = View.VISIBLE
                }

                is UserState.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                    setViewsVisibility(View.GONE)
                    binding.refresh.visibility = View.GONE
                }
                is UserState.Success -> {
                    setViewsVisibility(View.VISIBLE)
                    binding.refresh.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    showUserData(state.userEntity)

                }
            }
        }
        setFragmentResultListener(QrScanDestination.REQUEST_KEY) { _, bundle ->
            val qrData = QrScanDestination.getDataIfExist(bundle)
            println(qrData)
            val bundleToQrResult = bundleOf("qr_data" to qrData)
            findNavController().navigate(R.id.qrResultFragment, bundleToQrResult)

        }


    }

            private fun logout() {
                lifecycleScope.launch {
                    viewModel.clearUsername()
                    delay(50) // Не всегда успевает скинуть логин
                    findNavController().navigate(R.id.loginFragment)
                }

            }


            private fun showUserData(userEntity: UserEntity) {
                binding.apply {
                    fullname.text = userEntity.name
                    position.text = userEntity.position
                    lastEntry.text = viewModel.formatDate(userEntity.lastVisit)
                    Picasso.get().load(userEntity.photo).into(photo)

                    error.visibility = View.GONE
                    setViewsVisibility(View.VISIBLE)
                }
            }

            private fun showError() {
                binding.error.visibility = View.VISIBLE
                setViewsVisibility(View.GONE)
            }

            private fun setViewsVisibility(visibility: Int) {
                binding.fullname.visibility = visibility
                binding.position.visibility = visibility
                binding.lastEntry.visibility = visibility
                binding.photo.visibility = visibility
                binding.logout.visibility = visibility
                binding.scan.visibility = visibility
            }


            private fun onScanClick() {
                findNavController().navigate(R.id.qrScanFragment)
            }

            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }
        }

