package com.she.apps.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.she.apps.R
import com.she.apps.data.source.remote.request.EditGreenReq
import com.she.apps.data.source.remote.request.GreenReq
import com.she.apps.data.source.remote.response.GreenItem
import com.she.apps.databinding.ActivityGreenBinding
import com.she.apps.presentation.state.UiStateWrapper
import com.she.apps.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class GreenActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityGreenBinding::inflate)
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
        val green = intent.getParcelableExtra<GreenItem>("green")
        binding.apply {
            if (green != null) {
                btnSave.text = "Edit"
                etTgl.setText(changeDateFormat(green.date.toString(), "yyyy-MM-dd"))
                etJam.setText(changeDateFormat(green.date.toString(), "HH:mm:ss"))
                etShift.setText(green.shift)
                etSite.setText(green.site)
                etPelapor.setText(green.pelapor)
                etDp.setText(green.department)
                etLokasi.setText(green.lokasi)
                etKondisi.setText(green.kondisi)
                etSaran.setText(green.saran)
                etDibicarakan.setText(green.dibicarakan)
                etStatus.setText(green.status)
                etCategory.setText(green.kategori)
            } else {
                btnSave.text = "Simpan"
                btnHapus.visibility = View.GONE
            }
        }
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

            etShift.apply {
                setAdapter(
                    ArrayAdapter(
                        this@GreenActivity,
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
                        this@GreenActivity,
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
                        this@GreenActivity,
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
                        this@GreenActivity,
                        R.layout.item_dropdown,
                        Datas.status
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }
            etCategory.apply {
                setAdapter(
                    ArrayAdapter(
                        this@GreenActivity,
                        R.layout.item_dropdown,
                        Datas.category
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }

            btnHapus.setOnClickListener {
                val green = intent.getParcelableExtra<GreenItem>("green")
                viewModel.delGreen(green!!.id.toString())
            }

            btnSave.setOnClickListener {
                val green = intent.getParcelableExtra<GreenItem>("green")
                if (green != null) {
                    viewModel.editGreen(
                        EditGreenReq(
                            green.id!!.toInt(),
                            "${etTgl.text.toString()} ${etJam.text.toString()}",
                            etSaran.text.toString(),
                            etKondisi.text.toString(),
                            etLokasi.text.toString(),
                            etDibicarakan.text.toString(),
                            etCategory.text.toString(),
                            etStatus.text.toString(),
                            etShift.text.toString(),
                            etSite.text.toString(),
                            etDp.text.toString(),
                            etPelapor.text.toString()
                        )
                    )
                } else {
                    viewModel.addGreen(
                        GreenReq(
                            "${etTgl.text.toString()} ${etJam.text.toString()}",
                            etSaran.text.toString(),
                            etKondisi.text.toString(),
                            etLokasi.text.toString(),
                            etDibicarakan.text.toString(),
                            etCategory.text.toString(),
                            etStatus.text.toString(),
                            etShift.text.toString(),
                            etSite.text.toString(),
                            etDp.text.toString(),
                            etPelapor.text.toString()
                        )
                    )
                }
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
                        Toast.makeText(
                            this@GreenActivity,
                            "Berhasil Tambah Data",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@GreenActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }

        collectLifecycleFlow(viewModel.editGreen) { state ->
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
                        Toast.makeText(this@GreenActivity, "Berhasil Edit Data", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@GreenActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }

        collectLifecycleFlow(viewModel.delGreen) { state ->
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
                        Toast.makeText(
                            this@GreenActivity,
                            "Berhasil Hapus Data",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@GreenActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}