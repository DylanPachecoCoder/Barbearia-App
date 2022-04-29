package com.example.android.lesprojeto.ui.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.lesprojeto.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupToolbar()
        auth = FirebaseAuth.getInstance()

        setupListener()
    }

    private fun setupListener() {
        button_register_register.setOnClickListener {
            verifyPassword()
        }
    }

    private fun verifyPassword() {
        val email = textInputLayout_register_email.editText?.text.toString().trim()
        val password = textInputLayout_register_password.editText?.text.toString().trim()
        val confirmPassword =
            textInputLayout_register_repeat_password.editText?.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        } else if (password.contentEquals(confirmPassword)) {
            register()
        } else {
            Toast.makeText(this, "Senha diferente !", Toast.LENGTH_LONG).show()
        }
    }

    // configura título e botao da toolbar
    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = TOOLBAR_TITLE
    }

    private fun register() {
        auth.createUserWithEmailAndPassword(
            textInputLayout_register_email.editText?.text.toString().trim(),
            textInputLayout_register_password.editText?.text.toString().trim()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Cadastrado com sucesso !", Toast.LENGTH_LONG).show()
                finish()
            } else {
                verifyError(it.exception)
            }
        }
    }

    private fun verifyError(exception: Exception?) {
        val exceptionMessage = exception?.message.toString()

        val errorFormatted = when {
                exceptionMessage.contains("least 6 characters") -> "Digite uma senha maior que 5 characters"
                exceptionMessage.contains("address is badly") -> "E-mail inválido"
                exceptionMessage.contains("address is already") -> "E-mail já cadastrado"
                exceptionMessage.contains("interrupted connection") -> "sem conexão com a internet"
                else -> exceptionMessage
            }
        Toast.makeText(this, errorFormatted, Toast.LENGTH_LONG).show()
    }

    // configura o botao da toolbar para voltar para a tela anterior
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // tudo que está dentro do bloco companion object funciona como estático
    // é boa prática não usar hardcode e nem magic number,
    // sempre extrair as strings e valores numéricos para constantes
    companion object {
        private const val TOOLBAR_TITLE = "Cadastro"
    }
}