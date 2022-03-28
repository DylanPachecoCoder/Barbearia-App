package com.example.android.lesprojeto.ui.inicial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lesprojeto.R
import com.example.android.lesprojeto.ui.cadastro.CadastroActivity
import com.example.android.lesprojeto.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

//        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
//        val currentUser = auth.currentUser
//        if(currentUser != null){
////            reload();
//        }
    }
}
