package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanager.databinding.ActivityHomeScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeScreen : AppCompatActivity() {

    lateinit var dialog : Dialog
    lateinit var binding : ActivityHomeScreenBinding
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_dialogc))
        val buttonOk = dialog.findViewById<Button>(R.id.btnDialog)
        buttonOk.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnAdd.setOnClickListener {

            val name = binding.tVNameC.text.toString()
            val email = binding.tVEmailC.text.toString()
            val phoneNum = binding.tVPhoneC.text.toString()

            val people = Peoples(name, email, phoneNum)
            databaseReference = FirebaseDatabase.getInstance().getReference("Contacts")
            databaseReference.child(phoneNum).setValue(people).addOnSuccessListener {
                dialog.show()
                binding.tVNameC.text?.clear()
                binding.tVEmailC.text?.clear()
                binding.tVPhoneC.text?.clear()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed :(", Toast.LENGTH_SHORT).show()
            }

        }
    }
}