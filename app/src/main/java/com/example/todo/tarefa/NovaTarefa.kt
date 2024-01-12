package com.example.todo.tarefa

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.todo.MainActivity
import com.example.todo.databinding.FragmentNovaTarefaBinding
import com.example.todo.user.UserEntity
import com.example.todo.user.UserRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.time.LocalTime

class NovaTarefa(var tarefaItem: TarefaItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNovaTarefaBinding
    private lateinit var tarefaViewModel: TarefaViewModel
    private var prazo : LocalTime? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (tarefaItem != null){
            binding.tarefaTitulo.text = "Editar Tarefa"
            val editable = Editable.Factory.getInstance()
            binding.nome.text = editable.newEditable(tarefaItem!!.nome)
            binding.descricao.text = editable.newEditable(tarefaItem!!.descricao)
            if (tarefaItem!!.prazo() != null){
                prazo = tarefaItem!!.prazo()
                atualizaPrazoBt()
            }
        }else{
            binding.tarefaTitulo.text = "Nova Tarefa"
        }

        tarefaViewModel = ViewModelProvider(activity).get(TarefaViewModel::class.java)
        binding.salvarBt.setOnClickListener {
            salvarTarefa()
        }

        binding.selecionarHoraBt.setOnClickListener {
            selecionarHora()
        }
    }

    private fun selecionarHora() {
        if (prazo == null){
            prazo = LocalTime.now()
        }
        val listerner = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            prazo = LocalTime.of(hour, minute)
            atualizaPrazoBt()
        }
        val dialog = TimePickerDialog(activity, listerner, prazo!!.hour, prazo!!.minute, true)
        dialog.setTitle("Tarefa")
        dialog.show()
    }

    private fun atualizaPrazoBt() {
        binding.selecionarHoraBt.text = String().format("%02d:%02d", prazo!!.hour, prazo!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNovaTarefaBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun salvarTarefa() {
        val nome  = binding.nome.text.toString()
        val descricao = binding.descricao.text.toString()
        val prazoString = if (prazo == null) null else TarefaItem.horaFormatter.format(prazo)

        if (tarefaItem == null){
            val novaTarefa = TarefaItem(nome, descricao, prazoString, null)
            tarefaViewModel.adicionaTarefaItem(novaTarefa)
        }else{
            tarefaItem!!.nome = nome
            tarefaItem!!.descricao = descricao
            tarefaItem!!.prazoString = prazoString
            tarefaViewModel.atualizaTarefaItem(tarefaItem!!)
        }
        binding.nome.setText("")
        binding.descricao.setText("")
        dismiss()
    }

}