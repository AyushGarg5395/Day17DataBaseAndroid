package com.example.day17database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.day17database.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {

            val name = binding.eTName.text.toString()
            val mail = binding.eTMail.text.toString()
            val uniqueId = binding.eTUserName.text.toString()
            val password = binding.eTPassword.text.toString()

            val user= User(name, mail, password, uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user).addOnSuccessListener {
                binding.eTName.text?.clear()
                binding.eTMail.text?.clear()
                binding.eTPassword.text?.clear()
                binding.eTUserName.text?.clear()
                Toast.makeText(this,"user Registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}