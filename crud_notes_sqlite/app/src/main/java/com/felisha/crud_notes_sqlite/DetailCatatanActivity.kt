package com.felisha.crud_notes_sqlite

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailCatatanActivity : AppCompatActivity() {
    private lateinit var txtDetailJudulCatatan : TextView
    private lateinit var txtDetailPenulisCatatan : TextView
    private lateinit var txtDetailIsiCatatan: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_catatan)

        txtDetailJudulCatatan = findViewById(R.id.txtItemJudulCatatan)
        txtDetailPenulisCatatan = findViewById(R.id.txtItemPenulisCatatan)
        txtDetailIsiCatatan = findViewById(R.id.txtItemIsi)

        //get data
        val judulCatatan = intent.getStringExtra("judul")
        val penulisCatatan = intent.getIntExtra("penulis", 0).toString()
        val isiCatatan = intent.getStringExtra("isi")

        //set data ke widget
        txtDetailJudulCatatan.setText(judulCatatan)
        txtDetailPenulisCatatan.setText(penulisCatatan)
        txtDetailIsiCatatan.setText(isiCatatan)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}