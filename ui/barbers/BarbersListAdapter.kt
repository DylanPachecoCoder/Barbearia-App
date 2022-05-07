package com.example.android.lesprojeto.ui.barbers

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesprojeto.R
import com.example.android.lesprojeto.data.model.Barber
import kotlinx.android.synthetic.main.item_barber.view.*

class BarbersListAdapter(
    private val barbers: MutableList<Barber> = mutableListOf(),
    private val context: Context,
    var clickListener: (Barber) -> Unit = {}
) : RecyclerView.Adapter<BarbersListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.item_barber, parent, false)
        return ViewHolder(createdView)
    }

    override fun getItemCount(): Int {
        return barbers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedBarber = barbers[position]
        holder.bindView(selectedBarber, clickListener)
    }

    fun atualiza(barbers: List<Barber>) {
        notifyItemRangeRemoved(0, this.barbers.size)
        this.barbers.clear()
        this.barbers.addAll(barbers)
        notifyItemRangeInserted(0, this.barbers.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            selectedBarber: Barber,
            cliclListener: (Barber) -> Unit

        ) {
            itemView.item_barber_name.text = selectedBarber.name
            itemView.item_barber_specialty.text = selectedBarber.apelido

            itemView.setOnClickListener { cliclListener(selectedBarber) }
        }
    }
}

