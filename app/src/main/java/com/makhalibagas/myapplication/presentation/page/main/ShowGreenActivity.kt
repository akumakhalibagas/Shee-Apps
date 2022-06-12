package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityShowGreenBinding
import com.makhalibagas.myapplication.utils.viewBinding

class ShowGreenActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowGreenBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}