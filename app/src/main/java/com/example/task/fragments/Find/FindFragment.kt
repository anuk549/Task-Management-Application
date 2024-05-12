package com.example.task.fragments.Find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.model.Task
import com.example.task.viewmodel.TaskViewModel

class FindFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var inputTaskId: EditText
    private lateinit var searchButton: Button
    private lateinit var taskIdTextView: TextView
    private lateinit var taskNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var priorityTextView: TextView
    private lateinit var deadlineTextView: TextView
    private lateinit var noResultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find, container, false)

        // Initialize views
        inputTaskId = view.findViewById(R.id.input_task_id)
        searchButton = view.findViewById(R.id.search_button)
        taskIdTextView = view.findViewById(R.id.task_id_textview)
        taskNameTextView = view.findViewById(R.id.task_name_textview)
        descriptionTextView = view.findViewById(R.id.description_textview)
        priorityTextView = view.findViewById(R.id.priority_textview)
        deadlineTextView = view.findViewById(R.id.deadline_textview)
        noResultTextView = view.findViewById(R.id.no_result_textview)

        // Hide task details initially
        hideTaskDetails()

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Set click listener for the search button
        searchButton.setOnClickListener {
            val taskId = inputTaskId.text.toString().toIntOrNull()
            taskId?.let { searchTaskById(it) }
        }

        return view
    }

    private fun searchTaskById(taskId: Int) {
        taskViewModel.findTaskById(taskId).observe(viewLifecycleOwner, Observer { task ->
            if (task != null) {
                displayTaskDetails(task)
                noResultTextView.visibility = View.GONE
                showTaskDetails()
            } else {
                clearTaskDetails()
                noResultTextView.visibility = View.VISIBLE
                hideTaskDetails()
            }
        })
    }

    private fun displayTaskDetails(task: Task) {
        taskIdTextView.text = "Task ID: ${task.id}"
        taskNameTextView.text = "Task Name: ${task.tName}"
        descriptionTextView.text = "Description: ${task.description}"
        priorityTextView.text = "Priority: ${task.priority}"
        deadlineTextView.text = "Deadline: ${task.deadline}"
    }

    private fun clearTaskDetails() {
        taskIdTextView.text = ""
        taskNameTextView.text = ""
        descriptionTextView.text = ""
        priorityTextView.text = ""
        deadlineTextView.text = ""
    }

    private fun showTaskDetails() {
        taskIdTextView.visibility = View.VISIBLE
        taskNameTextView.visibility = View.VISIBLE
        descriptionTextView.visibility = View.VISIBLE
        priorityTextView.visibility = View.VISIBLE
        deadlineTextView.visibility = View.VISIBLE
    }

    private fun hideTaskDetails() {
        taskIdTextView.visibility = View.GONE
        taskNameTextView.visibility = View.GONE
        descriptionTextView.visibility = View.GONE
        priorityTextView.visibility = View.GONE
        deadlineTextView.visibility = View.GONE
    }
}
