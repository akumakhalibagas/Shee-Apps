package com.makhalibagas.myapplication.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.makhalibagas.myapplication.data.source.remote.request.EditJsaReq
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.databinding.ActivityJsaBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class JsaActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityJsaBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    lateinit var timePicker: TimePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        timePicker = TimePickerHelper(this, is24HourView = false, isSpinnerType = false)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        val jsa = intent.getParcelableExtra<JsaItem>("jsa")
        binding.apply {
            if (jsa != null) {
                btnSave.text = "Edit"
                etTgl.setText(changeDateFormat(jsa.tanggal.toString(), "yyyy-MM-dd"))
                etJam.setText(changeDateFormat(jsa.tanggal.toString(), "HH:mm:ss"))
                etPerusahaan.setText(jsa.perusahaan)
                etPekerja.setText(jsa.pekerja)
                etPekerjaan.setText(jsa.pekerjaan)
                etSupervisor.setText(jsa.supervisor)
                etShe.setText(jsa.department)
                etBahaya.setText(jsa.bahaya)
                etPengendalian.setText(jsa.pengendalian)
                etTanggungJawab.setText(jsa.tanggungJawab)
            } else {
                btnSave.text = "Simpan"
                btnHapus.visibility = View.GONE
            }
        }
    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                val hourStr = if (hourOfDay < 10) "0${hourOfDay}" else "${hourOfDay}"
                val minuteStr = if (minute < 10) "0${minute}" else "${minute}"
                binding.etJam.setText("${hourOfDay}:${minuteStr}:00")
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {

            etTgl.apply {
                onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        v?.showDatePicker {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateSelected: String = dateFormat.format(it.time)
                            etTgl.setText(dateSelected)
                        }
                    }
                }
                setOnClickListener {
                    showDatePicker {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val dateSelected: String = dateFormat.format(it.time)
                        etTgl.setText(dateSelected)
                    }
                }
            }

            etJam.apply {
                onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        showTimePickerDialog()
                    }
                }
                setOnClickListener {
                    showTimePickerDialog()
                }
            }

            btnHapus.setOnClickListener {
                val jsa = intent.getParcelableExtra<JsaItem>("jsa")
                viewModel.delJsa(jsa?.id.toString())
            }

            btnSave.setOnClickListener {
                val jsa = intent.getParcelableExtra<JsaItem>("jsa")
                if (jsa != null) {
                    viewModel.editJsa(
                        EditJsaReq(
                            jsa.id!!.toInt(),
                            etPekerja.text.toString(),
                            etPerusahaan.text.toString(),
                            etPekerjaan.text.toString(),
                            "${etTgl.text.toString()} ${etJam.text.toString()}}",
                            etSupervisor.text.toString(),
                            etShe.text.toString(),
                            etBahaya.text.toString(),
                            etPengendalian.text.toString(),
                            etTanggungJawab.text.toString(),
                        )
                    )
                } else {
                    viewModel.addJsa(
                        JsaReq(
                            etPekerja.text.toString(),
                            etPerusahaan.text.toString(),
                            etPekerjaan.text.toString(),
                            "${etTgl.text.toString()} ${etJam.text.toString()}}",
                            etSupervisor.text.toString(),
                            etShe.text.toString(),
                            etBahaya.text.toString(),
                            etPengendalian.text.toString(),
                            etTanggungJawab.text.toString(),
                        )
                    )
                }
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

        collectLifecycleFlow(viewModel.editJsa) { state ->
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
                        Toast.makeText(this@JsaActivity, "Berhasil Edit Data", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@JsaActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }

        collectLifecycleFlow(viewModel.delJsa) { state ->
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
                        Toast.makeText(this@JsaActivity, "Berhasil Hapus Data", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@JsaActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}