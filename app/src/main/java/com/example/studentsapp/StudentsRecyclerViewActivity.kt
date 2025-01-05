package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

        val addStudentButton: ImageButton = findViewById(R.id.students_recycler_view_activity_add_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTextView: TextView? = itemView.findViewById(R.id.student_row_name_text_view)
        private var idTextView: TextView? = itemView.findViewById(R.id.student_row_id_text_view)
        private var checkBox: CheckBox? = itemView.findViewById(R.id.student_row_check_box)
        private var student: Student? = null

        init {
            itemView.setOnClickListener {
                try {
                    if (student != null) {
                        val intent = Intent(itemView.context, StudentDetailsActivity::class.java)
                        // Log the data being passed
                        Log.d("StudentApp", "Passing student data: ${student?.name}, ${student?.id}")

                        // Add extras safely
                        student?.let { currentStudent ->
                            intent.putExtra("studentId", currentStudent.id ?: "")
                            intent.putExtra("studentName", currentStudent.name ?: "")
                            intent.putExtra("studentPhone", currentStudent.phone ?: "")
                            intent.putExtra("studentAddress", currentStudent.address ?: "")
                            intent.putExtra("isChecked", currentStudent.isChecked)
                        }

                        itemView.context.startActivity(intent)
                    } else {
                        Toast.makeText(itemView.context, "Error: Student data not available", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("StudentApp", "Error opening student details: ${e.message}")
                    Toast.makeText(itemView.context, "Error opening student details", Toast.LENGTH_SHORT).show()
                }
            }

            checkBox?.setOnClickListener { view ->
                try {
                    val isChecked = (view as CheckBox).isChecked
                    student?.isChecked = isChecked
                } catch (e: Exception) {
                    Log.e("StudentApp", "Error updating checkbox: ${e.message}")
                }
            }
        }

        fun bind(student: Student?) {
            try {
                this.student = student
                nameTextView?.text = student?.name ?: ""
                idTextView?.text = student?.id ?: ""
                checkBox?.isChecked = student?.isChecked ?: false
            } catch (e: Exception) {
                Log.e("StudentApp", "Error binding student data: ${e.message}")
            }
        }
    }

    class StudentsRecyclerAdapter(private val students: List<Student>?) :
        RecyclerView.Adapter<StudentViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.student_list_row, parent, false)
            return StudentViewHolder(view)
        }

        override fun getItemCount(): Int = students?.size ?: 0

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.bind(students?.get(position))
        }
    }
}