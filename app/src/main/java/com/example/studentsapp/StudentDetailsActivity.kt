package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val checkedButton: CheckBox = findViewById(R.id.student_details_activity_checked_button)
        val editButton: Button = findViewById(R.id.student_details_activity_edit_button)
        val nameEditText: EditText = findViewById(R.id.student_details_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.student_details_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.student_details_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.student_details_activity_address_edit_text)

        editButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val isChecked = checkedButton.isChecked

            val updatedStudentInfo = "Name: $name\n" +
                                     "ID: $id\n" +
                                     "Phone: $phone\n" +
                                     "Address: $address\n" +
                                     "Checked: $isChecked"
            Toast.makeText(this, "Student details updated!", Toast.LENGTH_SHORT).show()

            // Log or handle the updated student info
            println("Updated Student Info:\n$updatedStudentInfo")
        }
    }
}
