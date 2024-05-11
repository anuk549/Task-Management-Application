package com.example.task.fragments.Find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task.R
import kotlinx.android.synthetic.main.fragment_list.view.floatingActionButton1

class FindFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_find, container, false)

        // Ensure the FloatingActionButton is found
        view?.let {
            // Set click listener for the button
            it.floatingActionButton1?.setOnClickListener {
                findNavController().navigate(R.id.action_findFragment_to_listFragment)
            }
        }

        return view
    }
}
