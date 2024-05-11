package com.example.task.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.model.Task
import com.example.task.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val tName = view?.task_name_et?.text.toString() // Retrieve task name input
        val description = view?.description_et?.text.toString()
        val priority = view?.priority_et?.text.toString()
        val deadline = view?.deadline_et?.text.toString() // Retrieve deadline input

        if(inputCheck(tName,  description, priority)){
            // Create Task Object
            val task = Task(
                0,
                tName,
                description,
                priority,
                deadline
            )
            // Add Data to Database
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(tName: String,  description: String, priority: String): Boolean{
        return !(TextUtils.isEmpty(tName) || TextUtils.isEmpty(description) || TextUtils.isEmpty(priority))
    }

}
