package com.she.apps.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> ViewModel.collectLifecycleFlow(flow: Flow<T>, collect: FlowCollector<T>) {
    viewModelScope.launch {
        flow.collect(collect)
    }
}