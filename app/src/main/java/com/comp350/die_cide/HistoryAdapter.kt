/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.data.Interaction

class HistoryAdapter : ListAdapter<Interaction, HistoryAdapter.HistoryViewHolder>(InteractionsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.question, current.number, current.answer)
    }

    class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
        private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
        private val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)

        fun bind(question: String?, number: Int?, answer: String?) {
            answerTextView.text = answer
            numberTextView.text = number.toString()
            questionTextView.text = question
        }

        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.interaction_item, parent, false)

                return HistoryViewHolder(view)
            }
        }
        
        @Composable
        fun InteractionItem(interaction: Interaction, modifier: Modifier = Modifier) {
            Column(modifier.fillMaxWidth()) {
                Text(text = interaction.question)
                Text(text = interaction.number.toString())
                Text(text = interaction.answer)
            }
        }
    }

    class InteractionsComparator : DiffUtil.ItemCallback<Interaction>() {
        override fun areItemsTheSame(oldItem: Interaction, newItem: Interaction): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Interaction, newItem: Interaction): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
