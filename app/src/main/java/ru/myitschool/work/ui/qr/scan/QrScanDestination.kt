package ru.myitschool.work.ui.qr.scan

import android.os.Bundle
import androidx.core.os.bundleOf
import kotlinx.serialization.Serializable

// НЕ ИЗМЕНЯЙТЕ ЭТОТ ФАЙЛ. В ТЕСТАХ ОН БУДЕМ ВОЗВРАЩЁН В ИСХОДНОЕ СОСТОЯНИЕ
@Serializable
data object QrScanDestination {
    const val REQUEST_KEY = "qr_result"
    private const val KEY_QR_DATA = "key_qr"

    fun newInstance(): QrScanFragment {
        return QrScanFragment()
    }

    fun getDataIfExist(bundle: Bundle): String? {
        return if (bundle.containsKey(KEY_QR_DATA)) {
            bundle.getString(KEY_QR_DATA)
        } else {
            null
        }
    }

    internal fun packToBundle(data: String): Bundle {
        return bundleOf(
            KEY_QR_DATA to data
        )
    }
}