package com.example.todo.tarefa

import android.app.Application

class TarefaApp: Application() {

    private val db by lazy { TarefaItemDb.getDb(this) }
    val repository by lazy { TarefaItemRepository(db.tarefaItemDao()) }

}