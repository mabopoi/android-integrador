package com.example.notbored.presentation.activities.recyclerview

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.databinding.ActivitiesItemBinding
import com.example.notbored.presentation.SuggestionActivity

class ActivitiesItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ActivitiesItemBinding.bind(view)

    fun bind(activityName: String, participants: String? = null){
        binding.tvActivityName.text = activityName
        binding.container.setOnClickListener {
            //set Intent
            val intent = Intent(it.context, SuggestionActivity::class.java)
            intent.putExtra("type", activityName.lowercase())
            intent.putExtra("participants", participants)
            it.context.startActivity(intent)
        }
    }
}