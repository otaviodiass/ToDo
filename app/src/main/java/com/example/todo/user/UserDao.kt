package com.example.todo.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun salvarUsuario(userEntity: UserEntity)

    @Query("SELECT id FROM users WHERE email = :email")
    fun obterUserIdPorEmail(email: String): Int?

    @Query("Select * From users Where email=:email")
    fun buscaUsuario(email:String) : UserEntity

}