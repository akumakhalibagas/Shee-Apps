package com.makhalibagas.myapplication.presentation.page.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.data.source.remote.request.LoginReq
import com.makhalibagas.myapplication.databinding.ActivityLoginBinding
import com.makhalibagas.myapplication.presentation.page.main.MainActivity
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
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
                viewModel.login(LoginReq(etUsername.text.toString(), etPassword.text.toString()))
            }
        }
    }


    private fun initObserver() {
        collectLifecycleFlow(viewModel.login) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {

                }
                is UiStateWrapper.Success -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("users", state.data)
                    startActivity(intent)
                    finishAffinity()
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }
}