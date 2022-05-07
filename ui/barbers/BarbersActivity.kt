package com.example.android.lesprojeto.ui.barbers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lesprojeto.R
import com.example.android.lesprojeto.data.model.Barber
import com.example.android.lesprojeto.ui.barberservice.BarberServicesActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_barbers.*

class BarbersActivity : AppCompatActivity() {

    private val adapter by lazy {
        BarbersListAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barbers)
        setupToolbar()

        button_barbers_list_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }

        list_account_recyclerview.adapter = adapter
        adapter.atualiza(listOf(Barber(0,"Daniel","Especialista em cortes masculinos"),
            Barber(0,"Jackson","Especialista em coretes femininos")))

        adapter.clickListener = {
            val intent = Intent(this, BarberServicesActivity::class.java)
            intent.putExtra("BARBER_KEY", it)
            startActivity(intent)
        }
    }

    // configura título e botao da toolbar
    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = TOOLBAR_TITLE
    }

    // tudo que está dentro do bloco companion object funciona como estático
    // é boa prática não usar hardcode e nem magic number,
    // sempre extrair as strings e valores numéricos para constantes
    companion object {
        private const val TOOLBAR_TITLE = "Barbeiros"
    }
}