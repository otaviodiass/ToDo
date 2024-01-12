package com.example.todo.tarefa

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TarefaItemRepository(private val tarefaItemDao: TarefaItemDao) {

    val todasTarefaItem: Flow<List<TarefaItem>> = tarefaItemDao.todasTarefaItem()

    @WorkerThread
    suspend fun inserirTarefaItem(tarefaItem: TarefaItem){
        tarefaItemDao.inserirTarefaItem(tarefaItem)
    }

    @WorkerThread
    suspend fun atualizarTarefaItem(tarefaItem: TarefaItem){
        tarefaItemDao.atualizarTarefaItem(tarefaItem)
    }

}