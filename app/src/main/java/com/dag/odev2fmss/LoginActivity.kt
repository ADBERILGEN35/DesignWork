package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dag.odev2fmss.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mvIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mvIntent = Intent(this, SignUpActivity::class.java)

        binding.buttonNewAccount.setOnClickListener {
            startActivity(mvIntent)
            binding.textUserName.text?.clear()
            binding.textPassword.text?.clear()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        binding.buttonLogin.setOnClickListener {
            loginUser()
            binding.textUserName.text?.clear()
            binding.textPassword.text?.clear()
        }

        binding.buttonBack.setOnClickListener {
            onBackPressed()
            binding.textUserName.text?.clear()
            binding.textPassword.text?.clear()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }
    }

    fun loginUser() {
        val preferences = this.getSharedPreferences(
            "com.example.odev2fmss",
            Context.MODE_PRIVATE
        )
        var userName = binding.textUserName.text.toString()
        var password = binding.textPassword.text.toString()

        val preferenceUserName = preferences.getString("userName", "")
        val preferencePassword = preferences.getString("password", "")
        if (userName == preferenceUserName && password == preferencePassword) {
            Toast.makeText(this, "Başarıyla giriş yapılmıştır.", Toast.LENGTH_LONG).show()
        } else handleLoginFailuer(userName, password)

    }

    private fun handleLoginFailuer(userName: String, password: String) {
        if (userName == "") {
            binding.textUserName.requestFocus()
            binding.textUserName.setError("Kullanıcı adı boş bırakılamaz.")
        } else if (password == "") {
            binding.textPassword.requestFocus()
            binding.textPassword.setError("Şifre boş bırakılamaz.")
        } else {
            Toast.makeText(this, "Lütfen giriş bilgilerinizi kontrol ediniz.", Toast.LENGTH_LONG)
                .show()

        }
    }
}