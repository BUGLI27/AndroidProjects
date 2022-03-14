package com.example.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var firstname: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}