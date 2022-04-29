package com.example.android.lesprojeto.ui.inicial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lesprojeto.R
import com.example.android.lesprojeto.ui.barbers.BarbersActivity
import com.example.android.lesprojeto.ui.cadastro.RegisterActivity
import com.example.android.lesprojeto.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null

    // Funcao executada assim que a tela é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        if (user != null){
            startActivity(Intent(this, BarbersActivity::class.java))
            finish()
        }

        setupListeners()
    }

    // funcao com responsabilidade de configurar ação dos botoes
    private fun setupListeners() {

        // nomeDoBotao.setOnClickListener {} -> o que estiver dentro do bloco será executado
        // quando o botao for clicado
        button_main_login.setOnClickListener {
            // esse trecho de codigo starta uma nova activity (tela)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        button_main_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
