package ru.myitschool.work.ui.qr.scan

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentQrScanBinding
import ru.myitschool.work.utils.collectWhenStarted
import ru.myitschool.work.utils.visibleOrGone

// НЕ ИЗМЕНЯЙТЕ ЭТОТ ФАЙЛ. В ТЕСТАХ ОН БУДЕМ ВОЗВРАЩЁН В ИСХОДНОЕ СОСТОЯНИЕ
class QrScanFragment : Fragment(R.layout.fragment_qr_scan) {
    private var _binding: FragmentQrScanBinding? = null
    private val binding: FragmentQrScanBinding get() = _binding!!

    private var barcodeScanner: BarcodeScanner? = null
    private var isCameraInit: Boolean = false
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> viewModel.onPermissionResult(isGranted) }

    private val viewModel: QrScanViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQrScanBinding.bind(view)
        sendResult(bundleOf())
        subscribe()
        initCallback()
    }

    private fun initCallback() {
        binding.close.setOnClickListener { viewModel.close() }
    }

    private fun subscribe() {
        viewModel.state.collectWhenStarted(this) { state ->
            binding.loading.visibleOrGone(state is QrScanViewModel.State.Loading)
            binding.viewFinder.visibleOrGone(state is QrScanViewModel.State.Scan)
            if (!isCameraInit && state is QrScanViewModel.State.Scan) {
                startCamera()
                isCameraInit = true
            }
        }

        viewModel.action.collectWhenStarted(this) { action ->
            when (action) {
                is QrScanViewModel.Action.RequestPermission -> requestPermission(action.permission)
                is QrScanViewModel.Action.CloseWithCancel -> {
                    goBack()
                }
                is QrScanViewModel.Action.CloseWithResult -> {
                    sendResult(QrScanDestination.packToBundle(action.result))
                    goBack()
                }
            }
        }
    }

    private fun requestPermission(permission: String) {
        permissionLauncher.launch(permission)
    }

    private fun startCamera() {
        val context = requireContext()
        val cameraController = LifecycleCameraController(context)
        val previewView: PreviewView = binding.viewFinder
        val executor = ContextCompat.getMainExecutor(context)

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        val barcodeScanner = BarcodeScanning.getClient(options)
        this.barcodeScanner = barcodeScanner

        cameraController.setImageAnalysisAnalyzer(
            executor,
            MlKitAnalyzer(
                listOf(barcodeScanner),
                ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
                executor
            ) { result ->
                result?.getValue(barcodeScanner)?.firstOrNull()?.let { value ->
                    viewModel.findBarcode(value)

                }
            }
        )

        cameraController.bindToLifecycle(this)
        previewView.controller = cameraController
    }

    override fun onDestroyView() {
        barcodeScanner?.close()
        barcodeScanner = null
        _binding = null
        super.onDestroyView()
    }

    private fun goBack() {
        findNavControllerOrNull()?.popBackStack()
            ?: requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun sendResult(bundle: Bundle) {
        setFragmentResult(
            QrScanDestination.REQUEST_KEY,
            bundle
        )
        findNavControllerOrNull()
            ?.previousBackStackEntry
            ?.savedStateHandle
            ?.set(QrScanDestination.REQUEST_KEY, bundle)
    }

    private fun findNavControllerOrNull(): NavController? {
        return try {
            findNavController()
        } catch (_: Throwable) {
            null
        }
    }
}