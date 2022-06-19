package com.makhalibagas.myapplication.presentation.page.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.makhalibagas.myapplication.data.source.remote.request.LoginReq
import com.makhalibagas.myapplication.databinding.ActivityLoginBinding
import com.makhalibagas.myapplication.presentation.page.main.MainActivity
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.Shareds
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var shareds: Shareds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.apply {
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            btnSave.setOnClickListener {
                if (etUsername.text.toString().isEmpty() && etPassword.text.toString().isEmpty()){
                    Toast.makeText(this@LoginActivity, "Wajib isi semua", Toast.LENGTH_SHORT).show()
                }else {
                    viewModel.login(
                        LoginReq(
                            etUsername.text.toString(),
                            etPassword.text.toString()
                        )
                    )
                }
            }
        }
    }

    private fun initObserver() {
        collectLifecycleFlow(viewModel.login) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {
                    binding.apply {
                        loading.isVisible = state.isLoading
                        btnSave.isVisible = false
                    }
                }
                is UiStateWrapper.Success -> {
                    if (state.data.status.equals("fail")){
                        Toast.makeText(this, "error atau username password salah", Toast.LENGTH_SHORT).show()
                        binding.btnSave.isVisible = true
                    }else{
                        shareds.setUsers(Shareds.Key.users, state.data)
                        startActivity(Intent(this, MainActivity::class.java))
                        finishAffinity()
                    }
                }
                is UiStateWrapper.Error -> {
                    Toast.makeText(this, "error atau username password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}