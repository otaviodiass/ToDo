package com.example.todo.tarefa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TarefaItemBinding

class TarefaItemAdapter(
    private val tarefaItem: List<TarefaItem>,
    private val clickListerner: TarefaItemClickListerner
): RecyclerView.Adapter<TarefaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TarefaItemBinding.inflate(from, parent, false)
        return TarefaItemViewHolder(parent.context, binding, clickListerner)
    }

    override fun getItemCount(): Int = tarefaItem.size

    override fun onBindViewHolder(holder: TarefaItemViewHolder, position: Int) {
        holder.bindTarefaItem(tarefaItem[position])
    }
}