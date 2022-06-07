package com.example.trivia_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.select_quiz_view_holder.view.*


class SelectQuizAdapter (
    private val quizList: List<QuizItem>,
    var quizClickListener: QuizAdapterListener
    ) : RecyclerView.Adapter<SelectQuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): SelectQuizAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_quiz_view_holder, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int = quizList.size

    override fun onBindViewHolder(holder: SelectQuizAdapter.ViewHolder, position: Int) {
        holder.initialiser(quizList[position], quizClickListener)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.card_view_title
        val descriprionView: TextView = itemView.card_view_description

        fun initialiser(quizItem: QuizItem, action: QuizAdapterListener){
            titleView.text = quizItem.text
            descriprionView.text = quizItem.description
            titleView.setOnClickListener{
                view -> action.onQuizClick(quizItem, adapterPosition, view)
            }
        }
    }
    }

interface QuizAdapterListener{
    fun onQuizClick(chosenQuiz: QuizItem, position: Int, view: View)
}