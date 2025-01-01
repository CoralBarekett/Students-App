package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
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
            // Set click listener on the entire row (itemView)
            itemView.setOnClickListener {
                student?.let {
                    val intent = Intent(itemView.context, StudentDetailsActivity::class.java).apply {
                        putExtra("studentId", it.id)
                        putExtra("studentName", it.name)
                        putExtra("studentPhone", it.phone)
                        putExtra("studentAddress", it.address)
                        putExtra("isChecked", it.isChecked)
                    }
                    itemView.context.startActivity(intent)
                }
            }

            checkBox?.setOnClickListener { view ->
                val isChecked = (view as CheckBox).isChecked
                student?.isChecked = isChecked
            }
        }

        fun bind(student: Student?) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            checkBox?.isChecked = student?.isChecked ?: false
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