package ru.myitschool.work.ui.qr.scan

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.common.Barcode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.myitschool.work.utils.MutablePublishFlow

// НЕ ИЗМЕНЯЙТЕ ЭТОТ ФАЙЛ. В ТЕСТАХ ОН БУДЕМ ВОЗВРАЩЁН В ИСХОДНОЕ СОСТОЯНИЕ
class QrScanViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _action = MutablePublishFlow<Action>()
    val action = _action.asSharedFlow()

    private val _state = MutableStateFlow<State>(initialState)
    val state = _state.asStateFlow()

    init {
        checkPermission()
    }

    fun onPermissionResult(isGranted: Boolean) {
        viewModelScope.launch {
            if (isGranted) {
                _state.update { State.Scan }
            } else {
                _action.emit(Action.CloseWithCancel)
            }
        }
    }

    private fun checkPermission() {
        viewModelScope.launch {
            val isPermissionGranted = ContextCompat.checkSelfPermission(
                getApplication(),
                CAMERA_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
            if (isPermissionGranted) {
                _state.update { State.Scan }
            } else {
                delay(1000)
                _action.emit(Action.RequestPermission(CAMERA_PERMISSION))
            }
        }
    }

    fun findBarcode(barcode: Barcode) {
        viewModelScope.launch {
            barcode.rawValue?.let { value ->
                _action.emit(Action.CloseWithResult(value))
            }
        }
    }

    fun close() {
        viewModelScope.launch {
            _action.emit(Action.CloseWithCancel)
        }
    }

    sealed interface State {
        data object Loading : State

        data object Scan : State
    }

    sealed interface Action {
        data class RequestPermission(
            val permission: String
        ) : Action
        data object CloseWithCancel : Action
        data class CloseWithResult(
            val result: String
        ) : Action
    }

    private companion object {
        val initialState = State.Loading

        const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }
}