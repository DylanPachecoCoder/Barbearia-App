package com.example.android.lesprojeto.ui.barbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lesprojeto.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_barbers.*

class BarbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barbers)

        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
    }
}