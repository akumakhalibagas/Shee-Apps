package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityShowJsaBinding
import com.makhalibagas.myapplication.utils.viewBinding

class ShowJsaActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowJsaBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}