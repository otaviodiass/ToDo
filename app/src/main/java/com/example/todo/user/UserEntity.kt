package com.example.todo.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity(

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "senha")
    var senha: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) {

}