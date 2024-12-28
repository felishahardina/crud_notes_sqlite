package com.felisha.crud_notes_sqlite.screen_page

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.felisha.crud_notes_sqlite.model.ModelCatatan
import com.felisha.crud_notes_sqlite.databinding.ActivityUpdateCatatanBinding
import com.felisha.crud_notes_sqlite.helper.DbHelper

class UpdateCatatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateCatatanBinding
    private lateinit var db : DbHelper
    private var cttnId : Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCatatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)

        cttnId = intent.getIntExtra("id_cttn",-1)
        if(cttnId == -1){
            finish()
            return
        }
        //proses menampilkan data ke edit text di edit view

        val cttn = db.getCttnById(cttnId)
        binding.etEditJudul.setText(cttn.judul)
        binding.etEditPenulis.setText(cttn.penulis)
        binding.etEditIsi.setText(cttn.isi)

        //update dari button
        binding.btnEditCatatan.setOnClickListener(){
            val newJudul = binding.etEditJudul.text.toString()
            val newPenulis = binding.etEditPenulis.text.toString()
            val newIsi = binding.etEditIsi.text.toString()

            val updateCttn = ModelCatatan (cttnId, newJudul, newPenulis, newIsi)
            db.updateCatatan(updateCttn)
            finish()
            Toast.makeText(this,"update success", Toast.LENGTH_LONG).show()
        }
    }
}