package com.makhalibagas.myapplication.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.databinding.ActivityJsaBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.dateTime
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JsaActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityJsaBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Add Jsa"
        initListener()
        initObserver()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            btnSave.setOnClickListener {
                viewModel.addJsa(
                    JsaReq(
                        etPekerja.text.toString(),
                        etPekerja.text.toString(),
                        etPekerjaan.text.toString(),
                        dateTime(),
                        etSupervisor.text.toString(),
                        etHse.text.toString(),
                        etTahap.text.toString(),
                        etBahaya.text.toString(),
                        etPengendalian.text.toString(),
                        etTanggungJawab.text.toString(),
                        etAnggota.text.toString(),
                    )
                )
            }
        }
    }

    private fun initObserver() {
        collectLifecycleFlow(viewModel.addJsa) { state ->
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
                        Toast.makeText(this@JsaActivity, "Berhasil Tambah Data", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@JsaActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}