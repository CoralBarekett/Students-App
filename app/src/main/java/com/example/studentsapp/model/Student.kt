package com.example.studentsapp.model

import com.google.android.material.checkbox.MaterialCheckBox.CheckedState

data class Student(
    val name: String,
    val id: String,
    val phone: String,
    val address: String,
    val avatarUrl: String,
    var isChecked: Boolean
)
