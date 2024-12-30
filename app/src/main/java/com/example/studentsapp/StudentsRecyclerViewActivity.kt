package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsRecyclerViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students
        val recyclerView: RecyclerView = findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)
        recyclerView.adapter = adapter
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var nameTextView: TextView? = null
        private var idTextView: TextView? = null
        private var phoneTextView: TextView? = null
        private var addressTextView: TextView? = null
        private var checkBox: CheckBox? = null
        private var student: Student? = null

        init {
            nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
            idTextView = itemView.findViewById(R.id.student_row_id_text_view)
            phoneTextView = itemView.findViewById(R.id.student_row_phone_text_view)
            addressTextView = itemView.findViewById(R.id.student_row_address_text_view)
            checkBox = itemView.findViewById(R.id.student_row_check_box)

            checkBox?.apply {
                setOnClickListener { view ->
                    (tag as? Int)?.let { tag ->
                        student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                    }
                }
            }
            itemView.setOnClickListener{
                adapterPosition
            }
        }

        fun bind(student: Student?, position: Int) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            phoneTextView?.text = student?.phone
            addressTextView?.text = student?.address
            checkBox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }
    }

        class StudentsRecyclerAdapter(private val students: List<Student>?) :
            RecyclerView.Adapter<StudentViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
                val inflation = LayoutInflater.from(parent.context)
                val view = inflation.inflate(
                    R.layout.student_list_row,
                    parent,
                    false
                )


                return StudentViewHolder(view)
            }

            override fun getItemCount(): Int = students?.size ?: 0

            override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
                holder.bind(students?.get(position), position)
            }

        }
    }