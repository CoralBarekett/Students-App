package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.student_list_view)
        listView.adapter = StudentsAdapter()
    }

    class StudentsAdapter(): BaseAdapter() {
        override fun getCount(): Int = 10
        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflator = LayoutInflater.from(parent?.context)
            val view = convertView ?: inflator.inflate(R.layout.student_list_row, parent, false)

            val nameTextView: TextView = view.findViewById(R.id.student_row_name_text_view)
            val idTextView: TextView = view.findViewById(R.id.student_row_id_text_view)
            val phoneTextView: TextView =  view.findViewById(R.id.student_row_phone_text_view)
            val addressTextView: TextView = view.findViewById(R.id.student_row_address_text_view)

            val checkBox: CheckBox = view.findViewById(R.id.student_row_check_box)

            return view
        }
    }
}