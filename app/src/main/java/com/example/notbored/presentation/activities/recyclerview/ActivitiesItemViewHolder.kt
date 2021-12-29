package com.example.notbored.presentation.activities.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.databinding.ActivitiesItemBinding

class ActivitiesItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ActivitiesItemBinding.bind(view)

    fun bind(activityName: String){
        binding.tvActivityName.text = activityName
        binding.container.setOnClickListener {
            println(activityName)
            //set Intent
        }
    }
}