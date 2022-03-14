package com.example.jetpacktest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from book")
    fun loadAllBooks(): List<Book>

}