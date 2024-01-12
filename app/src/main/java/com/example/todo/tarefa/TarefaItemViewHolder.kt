package com.example.todo.tarefa

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TarefaItemBinding
import java.time.format.DateTimeFormatter

class TarefaItemViewHolder(
    private val context: Context,
    private val binding: TarefaItemBinding,
    private val clickListerner: TarefaItemClickListerner
):RecyclerView.ViewHolder(binding.root) {

    val horaFormat = DateTimeFormatter.ofPattern("HH:mm")
    fun bindTarefaItem(tarefaItem: TarefaItem){

        binding.nome.text = tarefaItem.nome

        if (tarefaItem.isCompleted()){
            binding.nome.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.prazo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeBt.setImageResource(tarefaItem.imagemResource())
        binding.completeBt.setColorFilter(tarefaItem.imagemCor(context))

        binding.completeBt.setOnClickListener {
            clickListerner.completarTarefa(tarefaItem)
        }

        binding.tarefaContainer.setOnClickListener{
            clickListerner.editarTarefa(tarefaItem)
        }

        if (tarefaItem.prazo() != null){
            binding.prazo.text = horaFormat.format(tarefaItem.prazo())
        } else {
            binding.prazo.text = ""
        }
    }

}