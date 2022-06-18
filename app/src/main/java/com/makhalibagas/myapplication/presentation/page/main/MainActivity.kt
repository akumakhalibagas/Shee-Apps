package com.makhalibagas.myapplication.presentation.page.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.databinding.ActivityMainBinding
import com.makhalibagas.myapplication.presentation.page.auth.LoginActivity
import com.makhalibagas.myapplication.utils.Shareds
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//head = show(1) admin/officer = show,crud(2)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var shareds: Shareds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
    }

    private fun initView() {
        val users = shareds.users!!
        if (users.tipeUser.equals("1")) {
            binding.lAdmin.visibility = View.GONE
        } else if (users.tipeUser.equals("2")) {
            binding.apply {
                mGc.visibility = View.GONE
                mIbpr.visibility = View.GONE
            }
        }
    }

    private fun initListener() {
        binding.apply {
            addGc.setOnClickListener {
                startActivity(Intent(this@MainActivity, GreenActivity::class.java))
            }

            addIbpr.setOnClickListener {
                startActivity(Intent(this@MainActivity, IbprActivity::class.java))
            }

            addJsa.setOnClickListener {
                startActivity(Intent(this@MainActivity, JsaActivity::class.java))
            }

            showGc.setOnClickListener {
                startActivity(Intent(this@MainActivity, ShowGreenActivity::class.java))
            }

            showIbpr.setOnClickListener {
                startActivity(Intent(this@MainActivity, ShowIbprActivity::class.java))
            }


            showJsa.setOnClickListener {
                startActivity(Intent(this@MainActivity, ShowJsaActivity::class.java))
            }


            mGc.setOnClickListener {
                val intent = Intent(this@MainActivity, MonitoringActivity::class.java)
                intent.putExtra("key", "gc")
                startActivity(intent)
            }


            mIbpr.setOnClickListener {
                val intent = Intent(this@MainActivity, MonitoringActivity::class.java)
                intent.putExtra("key", "mi")
                startActivity(intent)
            }

            logout.setOnClickListener {
                shareds.setUsers(Shareds.Key.users, null)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }
}