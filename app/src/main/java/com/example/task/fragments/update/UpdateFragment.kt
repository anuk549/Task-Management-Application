package com.example.task.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task.R
import com.example.task.model.Task
import com.example.task.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.updateTaskName_et.setText(args.currentTask.tName)
        view.updateDescription_et.setText(args.currentTask.description)
        view.updatePriority_et.setText(args.currentTask.priority)
        view.updateDeadline_et.setText(args.currentTask.deadline) // Set deadline EditText

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val tName = updateTaskName_et.text.toString()
        val description = updateDescription_et.text.toString()
        val priority = updatePriority_et.text.toString()
        val deadline = updateDeadline_et.text.toString() // Retrieve deadline input

        if (inputCheck(tName, description, priority)) {
            // Create Task Object
            val updatedTask = Task(args.currentTask.id, tName, description, priority, deadline) // Include deadline
            // Update Current Task
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(tName: String, description: String, priority: String): Boolean {
        return !(TextUtils.isEmpty(tName) && TextUtils.isEmpty(description) && TextUtils.isEmpty(priority))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentTask.tName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTask.tName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.tName}?")
        builder.create().show()
    }
}
