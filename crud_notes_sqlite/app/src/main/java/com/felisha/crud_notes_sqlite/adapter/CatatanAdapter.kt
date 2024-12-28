package com.felisha.crud_notes_sqlite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.felisha.crud_notes_sqlite.DetailCatatanActivity
import com.felisha.crud_notes_sqlite.model.ModelCatatan
import com.felisha.crud_notes_sqlite.screen_page.UpdateCatatanActivity
import com.felisha.crud_notes_sqlite.R
import com.felisha.crud_notes_sqlite.helper.DbHelper

class CatatanAdapter (

    private var listCatatan: List<ModelCatatan>,
    private var context: Context

) : RecyclerView.Adapter<CatatanAdapter.CatatanViewHolder>() {

    private val db: DbHelper = DbHelper(context)

    class CatatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtJudulCatatan: TextView = itemView.findViewById(R.id.txtItemJudulCatatan)
        val txtPenulis: TextView = itemView.findViewById(R.id.txtItemPenulisCatatan)
        val txtIsi: TextView = itemView.findViewById(R.id.txtItemIsi)
        val cardCatatan: CardView = itemView.findViewById(R.id.cardCatatan)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEditCatatan)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDeleteItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data_catatan,
            parent, false
        )
        return CatatanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCatatan.size
    }

    override fun onBindViewHolder(holder: CatatanViewHolder, position: Int) {
        val nCatatan = listCatatan[position]
        holder.txtJudulCatatan.text = nCatatan.penulis
        holder.txtPenulis.text = nCatatan.judul
        holder.txtIsi.text = nCatatan.isi

        holder.cardCatatan.setOnClickListener() {
            val intent = Intent(context, DetailCatatanActivity::class.java)

            intent.putExtra("judul", listCatatan[position].judul)
            intent.putExtra("penulis", listCatatan[position].penulis)
            intent.putExtra("isi", listCatatan[position].isi)
            context.startActivity(intent)

            Toast.makeText(context, listCatatan[position].judul, Toast.LENGTH_SHORT).show()
        }

        holder.btnDelete.setOnClickListener() {
            db.deleteCatatan(nCatatan.id)
            refreshData(db.getAllDataCatatan())
            Toast.makeText(
                holder.itemView.context,
                "Berhasil Delete Data ${nCatatan.judul}", Toast.LENGTH_LONG
            ).show()
        }
        //edit data
        holder.btnEdit.setOnClickListener() {
            //perlu bikin 1 lagi activity edit
            val intent = Intent(holder.itemView.context, UpdateCatatanActivity::class.java).apply {
                putExtra("id_cttn", nCatatan.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    //ini untuk refresh data
    fun refreshData (newCatatan: List<ModelCatatan>) {
        listCatatan = newCatatan
        notifyDataSetChanged()
    }
}