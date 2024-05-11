package com.example.task.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.model.Task
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.tName_txt.text = currentItem.tName
        holder.itemView.description_txt.text = currentItem.description
        holder.itemView.priority_txt.text = currentItem.priority
        holder.itemView.deadline_txt.text = currentItem.deadline

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}
