package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.databinding.ActivityIbprBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.dateTime
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IbprActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityIbprBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Add Ibpr"
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.apply {
            etLokasi.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etTemuan.isEnabled = true
            }
            etTemuan.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etResiko.isEnabled = true
            }
            etResiko.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etKodeBahaya.isEnabled = true
            }
            etKodeBahaya.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etPengendalianResiko.isEnabled = true
            }
            etPengendalianResiko.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etStatus.isEnabled = true
            }
            etStatus.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                btnSave.isEnabled = true
            }

            btnSave.setOnClickListener {
                viewModel.addIbpr(
                    IbprReq(
                        dateTime(),
                        etResiko.text.toString(),
                        etTemuan.text.toString(),
                        etPengendalianResiko.text.toString(),
                        etLokasi.text.toString(),
                        etKodeBahaya.text.toString(),
                        etStatus.text.toString()
                    )
                )
            }
        }
    }

    private fun initObserver() {
        collectLifecycleFlow(viewModel.addIbpr) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {
                    binding.apply {
                        loading.isVisible = state.isLoading
                        btnSave.isVisible = false
                    }
                }
                is UiStateWrapper.Success -> {
                    binding.apply {
                        loading.isVisible = false
                        btnSave.isVisible = true
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}