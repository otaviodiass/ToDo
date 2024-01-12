package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.ActivityTarefaBinding
import com.example.todo.tarefa.NovaTarefa
import com.example.todo.tarefa.TarefaApp
import com.example.todo.tarefa.TarefaItem
import com.example.todo.tarefa.TarefaItemAdapter
import com.example.todo.tarefa.TarefaItemClickListerner
import com.example.todo.tarefa.TarefaItemModelFactory
import com.example.todo.tarefa.TarefaViewModel

class TarefaActivity : AppCompatActivity(), TarefaItemClickListerner {

    private lateinit var binding: ActivityTarefaBinding

    private val tarefaViewModel: TarefaViewModel by viewModels {
        TarefaItemModelFactory((application as TarefaApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTarefaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.novaTarefaBt.setOnClickListener {
            NovaTarefa(null).show(supportFragmentManager, "novaTarefa")
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val tarefaActivity = this
        tarefaViewModel.tarefaItem.observe(this){
            binding.listaTarefas.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TarefaItemAdapter(it, tarefaActivity)
            }
        }
    }

    override fun editarTarefa(tarefaItem: TarefaItem) {
        NovaTarefa(tarefaItem).show(supportFragmentManager, "novaTarefa")
    }

    override fun completarTarefa(tarefaItem: TarefaItem) {
        tarefaViewModel.tarefaCompletada(tarefaItem)
    }
}