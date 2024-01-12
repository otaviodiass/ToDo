package com.example.todo.tarefa

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.todo.R
import com.example.todo.user.UserEntity
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "tarefa_item")

class TarefaItem (
    @ColumnInfo (name = "nome") var nome: String,
    @ColumnInfo (name = "descricao") var descricao: String,
    @ColumnInfo (name = "prazoString") var prazoString: String?,
    @ColumnInfo (name = "dataEntregaString") var dataEntregaString: String?,
    @PrimaryKey (autoGenerate = true) var id: Int = 0
){
    fun dataEntrega(): LocalDate? = if (dataEntregaString == null) null else LocalDate.parse(dataEntregaString, dataFormatter)
    fun prazo(): LocalTime? = if (prazoString == null) null else LocalTime.parse(prazoString, horaFormatter)

    fun isCompleted() = dataEntrega() != null
    fun imagemResource(): Int = if (isCompleted()) R.drawable.ic_check else R.drawable.ic_unchecked
    fun imagemCor(context: Context): Int = if (isCompleted()) black(context) else black(context)

    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object{
        val horaFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dataFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}