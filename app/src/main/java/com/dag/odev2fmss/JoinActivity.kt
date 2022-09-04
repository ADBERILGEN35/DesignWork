package com.dag.odev2fmss

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dag.odev2fmss.databinding.ActivityJoinBinding


class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private lateinit var mvIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mvIntent = Intent(this, LoginActivity::class.java)
        binding.buttonJoin.setOnClickListener {
            startActivity(mvIntent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}