package com.felisha.crud_notes_sqlite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.felisha.crud_notes_sqlite.adapter.CatatanAdapter
import com.felisha.crud_notes_sqlite.screen_page.TambahDataCatatan
import com.felisha.crud_notes_sqlite.databinding.ActivityMainBinding
import com.felisha.crud_notes_sqlite.helper.DbHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db :DbHelper
    private lateinit var catatanAdapter: CatatanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)
        catatanAdapter = CatatanAdapter(db.getAllDataCatatan(), this)

        binding.rvDataCatatan.layoutManager = LinearLayoutManager(this)
        binding.rvDataCatatan.adapter = catatanAdapter

        //silahkan buat detail page
        //ketika di klik itemnya akan pindah ke page lain

        binding.btnPageTambah.setOnClickListener{
            val intent = Intent(this, TambahDataCatatan::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val newCatatan = db.getAllDataCatatan()
        catatanAdapter.refreshData(newCatatan)
    }
}