package com.example.todo.user

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(var context: Context) {

    var dao: UserDao

    init {
        dao = UserDb.getDb(context).userDao()
    }

    suspend fun salvarUser(nome: String, email: String, senha: String): Any {

        return withContext(Dispatchers.IO) {
            if (nome != null && email != null && senha != null && email.count { it=='@' } == 1){
                val userEntity : UserEntity = UserEntity(nome, email, senha)
                return@withContext dao.salvarUsuario(userEntity)
            }
            return@withContext false
        }
    }

    suspend fun verificaLogin(email: String, senha: String) : Boolean{
        return withContext(Dispatchers.IO) {
            val userEntity: UserEntity? = dao.buscaUsuario(email)

            userEntity?.senha.equals(senha) ?: false
        }
    }
}

