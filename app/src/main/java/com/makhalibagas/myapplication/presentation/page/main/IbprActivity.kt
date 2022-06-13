package com.makhalibagas.myapplication.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.makhalibagas.myapplication.R
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.databinding.ActivityIbprBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class IbprActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityIbprBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    lateinit var timePicker: TimePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        timePicker = TimePickerHelper(this, is24HourView = false, isSpinnerType = false)
        supportActionBar?.title = "Add Ibpr"
        initListener()
        initObserver()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {

            etTgl.apply {
                onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if(hasFocus) {
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
                    if(hasFocus) {
                        showTimePickerDialog()
                    }
                }
                setOnClickListener {
                    showTimePickerDialog()
                }
            }

            etShift.apply {
                setAdapter(
                    ArrayAdapter(
                        this@IbprActivity,
                        R.layout.item_dropdown,
                        Datas.shift
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }
            etSite.apply {
                setAdapter(
                    ArrayAdapter(
                        this@IbprActivity,
                        R.layout.item_dropdown,
                        Datas.site
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }
            etDp.apply {
                setAdapter(
                    ArrayAdapter(
                        this@IbprActivity,
                        R.layout.item_dropdown,
                        Datas.departemen
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }
            etStatus.apply {
                setAdapter(
                    ArrayAdapter(
                        this@IbprActivity,
                        R.layout.item_dropdown,
                        Datas.status
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }
            btnSave.setOnClickListener {
                viewModel.addIbpr(
                    IbprReq(
                        "${etTgl.text.toString()} ${etTgl.text.toString()}}",
                        etResiko.text.toString(),
                        etTemuan.text.toString(),
                        etPengendalianResiko.text.toString(),
                        etLokasi.text.toString(),
                        etKodeBahaya.text.toString(),
                        etStatus.text.toString(),
//                        etShift.text.toString(),
//                        etSite.text.toString(),
//                        etDp.text.toString()
                    )
                )
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
                        Toast.makeText(this@IbprActivity, "Berhasil Tambah Data", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@IbprActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}