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

        // Initialize views
        val checkedButton: CheckBox = findViewById(R.id.student_details_activity_checked_button)
        val nameEditText: EditText = findViewById(R.id.student_details_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.student_details_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.student_details_activity_phone_edit_text)
        val addressEditText: EditText =
            findViewById(R.id.student_details_activity_address_edit_text)
        val editButton: Button = findViewById(R.id.student_details_activity_edit_button)

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

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("studentName", studentName)  // Replace student.name with your actual data
                putExtra("studentId", studentId)      // Replace student.id with your actual data
                putExtra(
                    "studentPhone",
                    studentPhone
                )  // Replace student.phone with your actual data
                putExtra(
                    "studentAddress",
                    studentAddress
                )  // Replace student.address with your actual data
                putExtra("isChecked", isChecked)  // Replace student.isChecked with your actual data
            }
            startActivity(intent)
        }
    }
}