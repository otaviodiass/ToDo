package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todo.databinding.ActivityFormCadastroBinding
import com.example.todo.user.UserRepository
import kotlinx.coroutines.launch

class CadastroActivity: AppCompatActivity() {

    lateinit var binding: ActivityFormCadastroBinding
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository(baseContext)

        binding.btCadastro.setOnClickListener {
            lifecycleScope.launch {
                val nome = binding.cadastraNome.text.toString()
                val email = binding.cadastraEmail.text.toString()
                val senha = binding.cadastraSenha.text.toString()

                if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                    userRepository.salvarUser(nome, email, senha)

                    Toast.makeText(this@CadastroActivity, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@CadastroActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(this@CadastroActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}