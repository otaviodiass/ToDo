package com.example.todo.tarefa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TarefaItem::class], version = 1, exportSchema = false)
abstract class TarefaItemDb: RoomDatabase() {
    abstract fun tarefaItemDao(): TarefaItemDao

    companion object{
        @Volatile
        private var INSTANCE: TarefaItemDb? = null

        fun getDb(context: Context): TarefaItemDb {
            return INSTANCE ?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarefaItemDb::class.java,
                    "tarefa_item"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}