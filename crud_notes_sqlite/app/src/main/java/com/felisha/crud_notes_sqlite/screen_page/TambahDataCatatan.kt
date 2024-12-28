package com.felisha.crud_notes_sqlite.screen_page

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.felisha.crud_notes_sqlite.model.ModelCatatan
import com.felisha.crud_notes_sqlite.databinding.ActivityTambahDataCatatanBinding
import com.felisha.crud_notes_sqlite.helper.DbHelper

class TambahDataCatatan : AppCompatActivity() {

    //binding : cara ringkas untuk kita deklarasi variable dan widget
    private lateinit var binding: ActivityTambahDataCatatanBinding
    private lateinit var db : DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataCatatanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = DbHelper(this )
        binding.btnTambahData.setOnClickListener{
            val judul = binding.txtInputJudul.text.toString()
            val penulis = binding.txtInputPenulis.text.toString()
            val isi = binding.txtInputIsi.text.toString()

            //karena nim --> int jadi kita perlu convert dari string ke int
            //toInt()
            val dataCatatan = ModelCatatan(0, judul, penulis, isi)
            db.insertDataCatatan(dataCatatan)
            finish();
            Toast.makeText(this, "Berhasil Tambah Data",
                Toast.LENGTH_LONG).show()
        }

    }
}