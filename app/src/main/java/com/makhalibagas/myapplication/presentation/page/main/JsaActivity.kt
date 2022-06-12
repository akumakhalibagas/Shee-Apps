package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityJsaBinding
import com.makhalibagas.myapplication.utils.viewBinding

class JsaActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityJsaBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Add Jsa"
    }
}