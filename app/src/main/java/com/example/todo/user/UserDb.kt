package com.example.todo.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDb: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDb? = null

        fun getDb(context: Context): UserDb{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDb::class.java,
                    "users"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
