package com.example.trivia_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.trivia_app.databinding.FragmentSelectQuizBinding
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A simple [Fragment] subclass.
 */
class SelectQuizFragment: Fragment(), QuizAdapterListener{
    private val viewModel: QuizGameViewModel by activityViewModels()
    private lateinit var binding: FragmentSelectQuizBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectQuizBinding.inflate(inflater, container, false)

        binding.quizList.adapter = SelectQuizAdapter(viewModel.quizChoices, this)
        binding.quizList.layoutManager = LinearLayoutManager(context)
        binding.quizList.setHasFixedSize(true)


        return binding.root
    }

    override fun onQuizClick(chosenQuiz: QuizItem, position: Int, view: View) {
        viewModel.setupGame(chosenQuiz.id)
        view.findNavController().navigate(R.id.navigate_to_selectQuizzQuiestionaire)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SelectQuizFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SelectQuizFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}