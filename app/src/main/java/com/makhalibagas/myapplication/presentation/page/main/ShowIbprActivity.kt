package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityShowIbprBinding
import com.makhalibagas.myapplication.utils.viewBinding

class ShowIbprActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowIbprBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}