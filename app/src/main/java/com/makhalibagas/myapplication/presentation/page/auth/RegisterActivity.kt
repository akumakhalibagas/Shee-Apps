package com.makhalibagas.myapplication.presentation.page.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.R
import com.makhalibagas.myapplication.data.source.remote.request.RegisterReq
import com.makhalibagas.myapplication.databinding.ActivityRegisterBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.Datas
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
        initObserver()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            etType.apply {
                setAdapter(
                    ArrayAdapter(
                        this@RegisterActivity,
                        R.layout.item_dropdown,
                        Datas.type
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }

            btnSave.setOnClickListener {
                if (etUsername.text.toString().isEmpty() && etPassword.text.toString().isEmpty() && etType.text.toString().isEmpty() ){
                    Toast.makeText(this@RegisterActivity, "Wajib isi semua", Toast.LENGTH_SHORT).show()
                }else{
                    val type = when {
                        etType.text.toString()=="Head" -> "1"
                        etType.text.toString()=="Officer/Admin" -> "2"
                        else -> "0"
                    }
                    viewModel.register(
                        RegisterReq(
                            etUsername.text.toString(),
                            etPassword.text.toString(),
                            type
                        )
                    )
                }
            }
        }
    }


    private fun initObserver() {
        collectLifecycleFlow(viewModel.register) { state ->
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
                    if (state.data.status.equals("fail")){
                        Toast.makeText(this, "error atau username sudah ada", Toast.LENGTH_SHORT).show()
                        binding.btnSave.isVisible = true
                    }else{
                        startActivity(Intent(this, LoginActivity::class.java))
                        finishAffinity()
                    }
                }
                is UiStateWrapper.Error -> {
                    Toast.makeText(this, "error atau username sudah ada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}