package com.example.todo.tarefa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class TarefaViewModel(private val repository: TarefaItemRepository): ViewModel() {

    var tarefaItem: LiveData<List<TarefaItem>> = repository.todasTarefaItem.asLiveData()


    fun adicionaTarefaItem(novaTarefa: TarefaItem) = viewModelScope.launch {
        repository.inserirTarefaItem(novaTarefa)
    }

    fun atualizaTarefaItem(tarefaItem: TarefaItem) = viewModelScope.launch {
        repository.atualizarTarefaItem(tarefaItem)
    }

    fun tarefaCompletada(tarefaItem: TarefaItem) = viewModelScope.launch {
        if (!tarefaItem.isCompleted())
            tarefaItem.dataEntregaString = TarefaItem.dataFormatter.format(LocalDate.now())
        repository.atualizarTarefaItem(tarefaItem)
    }
}

class TarefaItemModelFactory(private val repository: TarefaItemRepository): ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(TarefaViewModel::class.java))
            return TarefaViewModel(repository) as T

        throw IllegalAccessError("Erro na Classe ViewModel")
    }
}
