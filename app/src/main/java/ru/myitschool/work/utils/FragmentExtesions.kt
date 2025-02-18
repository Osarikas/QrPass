package ru.myitschool.work.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectWhenStarted(
    fragment: Fragment,
    crossinline collector: (T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        flowWithLifecycle(fragment.viewLifecycleOwner.lifecycle).collect { value ->
            collector.invoke(value)
        }
    }
}