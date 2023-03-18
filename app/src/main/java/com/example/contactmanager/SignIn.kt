package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLog.setOnClickListener {
            val unique = binding.tVUniqueIn.text.toString()
            if(unique.isNotEmpty()){
                readData(unique)
            }else{
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(unique: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(unique).get().addOnSuccessListener {
            if(it.exists()){
                val name = it.children.elementAt(1).value
                Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed, Try again later", Toast.LENGTH_SHORT).show()
        }
    }
}