package com.comp350.die_cide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.data.Interaction

class HistoryAdapter(private val list: List<Interaction>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.interaction_item, parent, false)

        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val interaction = list[position]

        holder.questionTextView.text = interaction.question
        holder.numberTextView.text = interaction.number.toString()
        holder.answerTextView.text = interaction.answer
    }

    class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
        val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
        val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
    }
}
