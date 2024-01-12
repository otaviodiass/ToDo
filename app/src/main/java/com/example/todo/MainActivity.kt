package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.user.UserRepository
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository(baseContext)

        registrarEventos()

    }

    private fun registrarEventos() {
        binding.BtLogin.setOnClickListener(this)
        binding.textViewCadastro.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.BtLogin.id -> executarLogin()
            binding.textViewCadastro.id -> executarCadastro()
        }
    }

    private fun executarCadastro() {
        val transicaoCadastro:Intent = Intent(baseContext, CadastroActivity::class.java)
        startActivity(transicaoCadastro)
    }

    private fun executarLogin() {

        lifecycleScope.launch {
            if(userRepository.verificaLogin(binding.EtEmail.text.toString(),
                    binding.EtSenha.text.toString())){
                val transicaoMensagem:Intent = Intent(baseContext, TarefaActivity::class.java)

                transicaoMensagem.putExtra("email", binding.EtEmail.text.toString())

                startActivity(transicaoMensagem)
            }else{
                Toast.makeText(baseContext, "dados do login incorretos",Toast.LENGTH_LONG).show()
            }
        }

    }

}