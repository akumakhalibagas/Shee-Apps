package com.makhalibagas.myapplication.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.makhalibagas.myapplication.R
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.databinding.ActivityGreenBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.Datas
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

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {

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
                        Toast.makeText(this@GreenActivity, "Berhasil Tambah Data", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@GreenActivity, MainActivity::class.java))
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}