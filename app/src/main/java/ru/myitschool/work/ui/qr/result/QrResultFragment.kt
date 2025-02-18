package ru.myitschool.work.ui.qr.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentQrResultBinding
import ru.myitschool.work.domain.entities.OpenEntity
import ru.myitschool.work.utils.collectWhenStarted

class QrResultFragment : Fragment(R.layout.fragment_qr_result) {
    private var _binding: FragmentQrResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<QrResultViewModel>{ QrResultViewModel.Factory }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQrResultBinding.bind(view)
        var qrData = arguments?.getString("qr_data")
        if (qrData != null) {
            viewModel.openDoor(OpenEntity(qrData.toLong()))
        }
        else{
            binding.result.text = getString(R.string.result_null_text)
        }
        viewModel.state.collectWhenStarted(this){ state->
            when(state) {
                QrResultViewModel.State.Error -> {
                    binding.result.text = getString(R.string.result_fail_text)
                }
                QrResultViewModel.State.Loading -> {
                    Unit
                }
                QrResultViewModel.State.Success -> {
                    binding.result.text = getString(R.string.result_success_text)
                }
            }
            binding.close.setOnClickListener{
                qrData = ""
                findNavController().navigate(R.id.mainFragment)
            }

        }
    }
}