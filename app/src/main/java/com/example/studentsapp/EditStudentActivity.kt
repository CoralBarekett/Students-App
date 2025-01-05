package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        val checkedButton: CheckBox = findViewById(R.id.edit_student_activity_checked_button)
        val cancelButton: Button = findViewById(R.id.edit_student_activity_cancel_button)
        val saveButton: Button = findViewById(R.id.edit_student_activity_save_button)
        val deleteButton: Button = findViewById(R.id.edit_student_activity_delete_button)
        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text)
        val saveMessageTextView: TextView = findViewById(R.id.edit_student_activity_save_message_text_view)


        // Retrieve data from intent
        val studentName = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")
        val studentPhone = intent.getStringExtra("studentPhone")
        val studentAddress = intent.getStringExtra("studentAddress")
        val isChecked = intent.getBooleanExtra("isChecked", false)

        // Set the data to the views
        studentName?.let { nameEditText.setText(it) }
        studentId?.let { idEditText.setText(it) }
        studentPhone?.let { phoneEditText.setText(it) }
        studentAddress?.let { addressEditText.setText(it) }
        checkedButton.isChecked = isChecked

        cancelButton.setOnClickListener{
            finish()
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = if (checkedButton.isChecked) "Checked" else "Unchecked"

            saveMessageTextView.text = "Name: $name, ID: $id, Phone: $phone, Address: $address, Status: $isChecked saved!"
        }

        deleteButton.setOnClickListener {
            saveMessageTextView.text = ""  // מוחק את התוכן של ה-TextView
        }

    }
}