package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database : DatabaseReference
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val name = binding.tVName.text.toString()
            val email = binding.tVEmail.text.toString()
            val pass = binding.tVPassword.text.toString()
            val unique = binding.tVUnique.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")

            val user = User(name, email, pass, unique)
            database.child(unique).setValue(user).addOnSuccessListener {
                binding.tVName.text?.clear()
                binding.tVEmail.text?.clear()
                binding.tVPassword.text?.clear()
                binding.tVUnique.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed :(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}