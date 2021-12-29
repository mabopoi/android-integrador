package com.example.notbored.presentation.activities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.R

class ActivitiesAdapter(private val activitiesList: List<String>) :
    RecyclerView.Adapter<ActivitiesItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activities_item, parent, false)

        return ActivitiesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivitiesItemViewHolder, position: Int) {
        val item = activitiesList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = activitiesList.size
}