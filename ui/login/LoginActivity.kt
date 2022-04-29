package com.example.android.lesprojeto.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.lesprojeto.R
import com.example.android.lesprojeto.ui.barbers.BarbersActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        
        setupToolbar()
        setupListener()
    }

    private fun setupListener() {
        button_login_login.setOnClickListener {
            loginEmail()
        }

    }

    private fun loginEmail() {
        val email = textInputLayout_login_email.editText?.text.toString().trim()
        val password = textInputLayout_login_password.editText?.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha os campos obrigatórios" , Toast.LENGTH_LONG).show()
        }else{
            loginAuthentication(email, password)
        }
    }

    private fun loginAuthentication(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, BarbersActivity::class.java))
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
            exceptionMessage.contains("interrupted connection") -> "sem conexão com a internet"
            exceptionMessage.contains("password is invalid") -> "Senha inválida"
            exceptionMessage.contains("There is no user") -> "Este e-mail não está cadastrado"
            else -> exceptionMessage
        }
        Toast.makeText(this, errorFormatted, Toast.LENGTH_LONG).show()
    }


    // configura título e botao da toolbar
    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = TOOLBAR_TITLE
    }

    // configura o botao da toolbar para voltar para a tela anterior
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // tudo que está dentro do bloco companion object funciona como estático
    // é boa prática não usar hardcode e nem magic number,
    // sempre extrair as strings e valores numéricos para constantes
    companion object{
        private const val TOOLBAR_TITLE = "Log in"
    }
}