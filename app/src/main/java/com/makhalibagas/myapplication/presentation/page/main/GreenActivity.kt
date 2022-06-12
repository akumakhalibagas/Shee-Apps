package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.databinding.ActivityGreenBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.dateTime
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreenActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityGreenBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Add Green"
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.apply {
            etLokasi.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etKondisi.isEnabled = true
            }
            etKondisi.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etSaran.isEnabled = true
            }
            etSaran.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etDibicarakan.isEnabled = true
            }
            etDibicarakan.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etStatus.isEnabled = true
            }
            etStatus.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                etCategory.isEnabled = true
            }
            etCategory.doOnTextChanged { text, _, _, _ ->
                if (text != null && text.contains(',') || text?.length ?: 0 < 4) return@doOnTextChanged
                btnSave.isEnabled = true
            }

            btnSave.setOnClickListener {
                viewModel.addGreen(
                    GreenReq(
                        dateTime(),
                        etSaran.text.toString(),
                        etKondisi.text.toString(),
                        etLokasi.text.toString(),
                        etDibicarakan.text.toString(),
                        etCategory.text.toString(),
                        etStatus.text.toString()
                    )
                )
            }
        }
    }

    private fun initObserver() {
        collectLifecycleFlow(viewModel.addGreen) { state ->
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