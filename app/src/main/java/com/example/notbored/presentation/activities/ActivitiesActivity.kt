package com.example.notbored.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.notbored.databinding.ActivitiesToolbarBinding
import com.example.notbored.databinding.ActivityActivitiesBinding
import com.example.notbored.presentation.SuggestionActivity
import com.example.notbored.presentation.activities.recyclerview.ActivitiesAdapter

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesBinding
    private val activitiesList = listOf(
        "Education", "Recreational", "Social", "Diy",
        "Charity", "Cooking", "Relaxation", "Music", "Busywork"
    )
    private var participants: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up custom toolbar
        val toolbarBinding = ActivitiesToolbarBinding.inflate(layoutInflater)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = toolbarBinding.root

        participants = intent.getStringExtra("participants")

        toolbarBinding.btnRandom.setOnClickListener {
            //Set Intent to Random screen
            val intent = Intent(this, SuggestionActivity::class.java)
            intent.putExtra("participants", participants)
            startActivity(intent)
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvActivities.adapter = ActivitiesAdapter(activitiesList, participants)
    }
}