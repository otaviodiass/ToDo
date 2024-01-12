package com.example.todo.tarefa

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaItemDao {

    @Query("SELECT * FROM tarefa_item ORDER BY id ASC")
    fun todasTarefaItem(): Flow<List<TarefaItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirTarefaItem(tarefaItem: TarefaItem)

    @Update
    suspend fun atualizarTarefaItem(tarefaItem: TarefaItem)

}