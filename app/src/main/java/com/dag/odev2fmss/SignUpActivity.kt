package com.dag.odev2fmss

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dag.odev2fmss.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    var emailId: String? = null
    var userName: String? = null
    var password: String? = null

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mvIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mvIntent = Intent(this, LoginActivity::class.java)

        binding.buttonBackSecond.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        binding.buttonSignUp.setOnClickListener {
            signUp()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    fun signUp() {
        val sharedPreferences = this.getSharedPreferences(
            "com.example.odev2fmss",
            Context.MODE_PRIVATE
        )
        emailId = binding.editTextEmailAddress.text.toString()
        userName = binding.editTextUserName.text.toString()
        password = binding.editTextPassword.text.toString()

        if ((emailId != "") && (userName != "") && (password != "")) {
            handleSignUp(sharedPreferences)
        } else {
            signUpFailure()
        }
    }

    /**
     * Sign up failure
     *
     * editText empty control
     *
     */
    private fun signUpFailure() {
        if (emailId == "") {
            binding.editTextEmailAddress.requestFocus()
            binding.editTextEmailAddress.setError("Email boş bırakılamaz.")
        } else if (userName == "") {
            binding.editTextUserName.requestFocus()
            binding.editTextUserName.setError("Kullanıcı adı boş bırakılamaz.")
        } else {
            binding.editTextPassword.requestFocus()
            binding.editTextPassword.setError("Şifre boş bırakılamaz.")
        }
    }

    /**
     * Handle sign up
     *
     * New user creation function
     *
     * @param sharedPreferences
     */
    private fun handleSignUp(sharedPreferences: SharedPreferences) {
        val alert = AlertDialog.Builder(this@SignUpActivity)
        alert.setTitle("Yeni kullanıcı kaydı oluşturulsun mu ?")
        alert.setMessage(
            "Email : $emailId \n" +
                    "Kullanıcı Adı : $userName \n" +
                    "Şifre : $password"
        )
        alert.setPositiveButton("Evet") { dialog: DialogInterface, a: Int ->
            sharedPreferences.edit().putString("emailId", emailId).apply()
            sharedPreferences.edit().putString("userName", userName).apply()
            sharedPreferences.edit().putString("password", password).apply()
            Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_LONG).show()
            finish()
        }
        alert.setNegativeButton("Hayır") { dialog: DialogInterface, a: Int ->
            Toast.makeText(this, "Kayıt Gerçekleştirilemedi", Toast.LENGTH_LONG).show()
            binding.editTextUserName.text?.clear()
            binding.editTextPassword.text?.clear()
            binding.editTextEmailAddress.text?.clear()
        }
        alert.show()
    }
}