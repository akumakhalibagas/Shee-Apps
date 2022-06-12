package com.makhalibagas.myapplication.presentation.page.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.data.source.remote.response.Users
import com.makhalibagas.myapplication.databinding.ActivityMainBinding
import com.makhalibagas.myapplication.presentation.page.auth.LoginActivity
import com.makhalibagas.myapplication.utils.Shareds
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    @Inject
    lateinit var shareds: Shareds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        val users = shareds.users!!
        binding.apply {
            btGreen.setOnClickListener {
                if (users.tipeUser.equals("2")) {
                    startActivity(Intent(this@MainActivity, GreenActivity::class.java))
                } else if (users.tipeUser.equals("1")) {
                    startActivity(Intent(this@MainActivity, ShowGreenActivity::class.java))
                }
            }
            btIbpr.setOnClickListener {
                if (users.tipeUser.equals("2")) {
                    startActivity(Intent(this@MainActivity, IbprActivity::class.java))
                } else if (users.tipeUser.equals("1")) {
                    startActivity(Intent(this@MainActivity, ShowIbprActivity::class.java))
                }
            }
            btJsa.setOnClickListener {
                if (users.tipeUser.equals("2")) {
                    startActivity(Intent(this@MainActivity, JsaActivity::class.java))
                } else if (users.tipeUser.equals("1")) {
                    startActivity(Intent(this@MainActivity, ShowJsaActivity::class.java))
                }
            }

            btLogout.setOnClickListener {
                shareds.setUsers(Shareds.Key.users, null)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }
}