package com.example.trivia_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.trivia_app.databinding.FragmentAnswerQuizzQuiestionaireBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SelectQuizzQuiestionaire.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectQuizzQuiestionaire : Fragment() {
    // TODO: place GameViewModel
    private val viewModel: QuizGameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //TODO: Bind the score, the text, the radioGroup, and the button to the ViewModel.
        val binding =  FragmentAnswerQuizzQuiestionaireBinding.inflate(inflater, container, false)

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.score.text = "`Score: $newScore"
        })

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.textView3.text = newQuestion.question
            val radioGroup = binding.radioGroup
            radioGroup.removeAllViews()
            for ((Index, answer) in newQuestion.answers.withIndex()){
               //TODO: Create private function for createRadioButton
                val newRadioButton = createRadiobutton(answer, Index)
                radioGroup.addView(newRadioButton)
            }
        })

        binding.button.setOnClickListener{view: View ->
            val id = binding.radioGroup.checkedRadioButtonId
            viewModel.checkQuestion(id)

            if(viewModel.currentQuestion.value!! < viewModel.amountOfQuestion.value!! - 1){
                viewModel.updateQuestion(viewModel.currentQuestion.value?: 0)
            } else {
                if(viewModel.score.value!! >= 30){
                    view.findNavController().navigate(R.id.navigate_to_quizSuccessFragment)
                } else {
                    view.findNavController().navigate(R.id.navigate_to_quizFailureFragment)
                }
            }
        }

        return binding.root
    }

    private fun createRadiobutton(answer: String, id: Int) : RadioButton{
        val rdb = RadioButton(context)
        rdb.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rdb.text = answer
        rdb.id = id
        return rdb
    }

}