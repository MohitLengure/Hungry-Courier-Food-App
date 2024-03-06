package com.example.food_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.food_app.bottom_fragment.HomeFragment
import com.example.food_app.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginPage : AppCompatActivity() {

    private val binding:ActivityLoginPageBinding by lazy {
        ActivityLoginPageBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

        override fun onStart() {
            super.onStart()
            //check if user is already login
            val currentUser: FirebaseUser? = auth.currentUser
            if (currentUser != null) {
                startActivity(Intent(this, HomeFragment::class.java))
                finish()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize
        auth=FirebaseAuth.getInstance()



        binding.LoginButton.setOnClickListener{

            val username=binding.loginUsername.text.toString()
            val password=binding.loginPassword.text.toString()

            if(username.isEmpty()||password.isEmpty())
            {
                Toast.makeText(this,"Please Fill All The Details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener {task->

                        if (task.isSuccessful){
                            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(Intent(this,HomeScreen::class.java));
                            finish();
                        }
                        else
                            if (task.isCanceled)
                            {
                                Toast.makeText(this,"Login Details Incorrect",Toast.LENGTH_SHORT).show();
                            }
                        else{
                            Toast.makeText(this,"Login Failed: ${task.exception?.message}",Toast.LENGTH_SHORT).show();
                        }
                    }
            }

        }
        binding.signupRedirectText.setOnClickListener{
            startActivity(Intent(this,signupActivity::class.java));
            finish()
        }


        binding.forgotPassword.setOnClickListener{
            startActivity(Intent(this,Forgot_Password::class.java));
            finish();
        }






    } }