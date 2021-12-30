package com.example.notbored.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.notbored.databinding.ActivitiesToolbarBinding
import com.example.notbored.databinding.ActivityActivitiesBinding
import com.example.notbored.presentation.activities.recyclerview.ActivitiesAdapter

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesBinding
    private val activitiesList = listOf(
        "Education", "Recreational", "Social", "Diy",
        "Charity", "Cooking", "Relaxation", "Music", "Busywork"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up custom toolbar
        val toolbarBinding = ActivitiesToolbarBinding.inflate(layoutInflater)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = toolbarBinding.root

        toolbarBinding.btnRandom.setOnClickListener {
            //Set Intent to Random screen
        }

        //intent.getStringExtra("participants")

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvActivities.adapter = ActivitiesAdapter(activitiesList)
    }
}