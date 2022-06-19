package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityMonitoringBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonitoringActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMonitoringBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObserver()
    }

    private fun initObserver() {
        val key = intent.getStringExtra("key")
        if (key == "gc") {
            binding.tvStatusMon.text = "Status Green Card"
            viewModel.getMonGreen()
            collectLifecycleFlow(viewModel.greenMon) { state ->
                when (state) {
                    is UiStateWrapper.Loading -> {}
                    is UiStateWrapper.Success -> {
                        binding.apply {
                            tvOpen.text = "Open\n${state.data.persenOpen}%"
                            tvClose.text = "Close\n${state.data.persenClose}%"
                            tvContinue.text = "Continue\n${state.data.persenContinue}%"
                        }
                    }
                    is UiStateWrapper.Error -> {}
                }
            }
        } else if (key == "mi") {
            binding.tvStatusMon.text = "Status IBPR"
            viewModel.getMonIbpr()
            collectLifecycleFlow(viewModel.ibprMon) { state ->
                when (state) {
                    is UiStateWrapper.Loading -> {}
                    is UiStateWrapper.Success -> {
                        binding.apply {
                            tvOpen.text = "Open\n${state.data.persenOpen}%"
                            tvClose.text = "Close\n${state.data.persenClose}%"
                            tvContinue.text = "Continue\n${state.data.persenContinue}%"
                        }
                    }
                    is UiStateWrapper.Error -> {}
                }
            }
        }
    }
}