    package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val get_started=findViewById<Button>(R.id.get_started);

        get_started.setOnClickListener{
            val intent1 = Intent(this, signupActivity::class.java)
            startActivity(intent1);
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show();
        }


    }
}