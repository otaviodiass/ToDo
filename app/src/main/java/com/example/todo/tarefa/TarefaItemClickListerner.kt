package com.example.todo.tarefa

interface TarefaItemClickListerner {

    fun editarTarefa(tarefaItem: TarefaItem)
    fun completarTarefa(tarefaItem: TarefaItem)

}