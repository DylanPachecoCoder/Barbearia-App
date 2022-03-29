package com.example.android.lesprojeto.ui.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lesprojeto.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupToolbar()
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
        private const val TOOLBAR_TITLE = "Cadastro"
    }
}