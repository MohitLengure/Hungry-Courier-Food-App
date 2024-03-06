package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.food_app.databinding.ActivityForgotPasswordBinding
import com.example.food_app.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class Forgot_Password : AppCompatActivity() {
    private val binding: ActivityForgotPasswordBinding by lazy {
        ActivityForgotPasswordBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize
        auth=FirebaseAuth.getInstance()

        binding.btnReset.setOnClickListener{
            val email=binding.emailBox.text.toString();
            if(email.isEmpty())
            {
                Toast.makeText(this,"Please Email id", Toast.LENGTH_SHORT).show();
            }
            else
            {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"You will receive your password reset mail soon !",Toast.LENGTH_SHORT).show();
                            startActivity(Intent(this,LoginPage::class.java));
                            finish();
                        }
                        else{
                            Toast.makeText(this,"Failed to send mail: ${task.exception?.message}",Toast.LENGTH_SHORT).show();
                        }
                    }
            }

            binding.btnCancel.setOnClickListener{
                startActivity(Intent(this,LoginPage::class.java));
                finish();
            }

        }


    }
}