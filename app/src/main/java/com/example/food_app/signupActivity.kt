package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.food_app.databinding.ActivitySignupBinding
import com.google.android.gms.common.internal.BinderWrapper
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {

private val binding:ActivitySignupBinding by lazy {
    ActivitySignupBinding.inflate(layoutInflater)
}

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize firebase auth
        auth=FirebaseAuth.getInstance();

        binding.signupButton.setOnClickListener{
            val name:String=binding.signupName.text.toString()
            val email:String=binding.signupEmail.text.toString()
            val password:String=binding.Password.text.toString()
            val repassword:String=binding.RetypePassword.text.toString()


            //check if any field is black
            if(name.isEmpty()||email.isEmpty()||password.isEmpty()||repassword.isEmpty())
            {
                Toast.makeText(this,"Please Fill All The Details",Toast.LENGTH_SHORT).show();
            }else
                if(password != repassword)
                {
                    Toast.makeText(this,"Repeat Password is not match",Toast.LENGTH_SHORT).show();
                }
            else
                {
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){ task ->
                            if(task.isSuccessful)
                            {
                                Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(Intent(this,LoginPage::class.java));
                                finish();
                            }
                            else{
                                Toast.makeText(this,"Registration Failed: ${task.exception?.message}",Toast.LENGTH_SHORT).show();
                            }

                        }
                }


        }
        binding.loginRedirectText.setOnClickListener {
            startActivity(Intent(this,LoginPage::class.java));
            finish();
            Toast.makeText(this,"Button Clicked", Toast.LENGTH_SHORT).show();
        }



    }
}