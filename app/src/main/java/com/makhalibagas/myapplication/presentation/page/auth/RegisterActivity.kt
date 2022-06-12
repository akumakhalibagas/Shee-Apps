package com.makhalibagas.myapplication.presentation.page.auth

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.data.source.remote.request.RegisterReq
import com.makhalibagas.myapplication.databinding.ActivityRegisterBinding
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
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
        supportActionBar?.hide()
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.apply {
            btnSave.setOnClickListener {
                viewModel.register(
                    RegisterReq(
                        etUsername.text.toString(),
                        etPassword.text.toString(),
                        "2"
                    )
                )
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
                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAffinity()
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}