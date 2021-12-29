package com.example.notbored.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.notbored.R
import com.example.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etParticipants.addTextChangedListener {
            println(it.toString()) //validations for button
        }

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, ActivitiesActivity::class.java)
            val participants = binding.etParticipants.text.toString()
            intent.putExtra("participants", participants)
            startActivity(intent)
        }

        binding.tvTermsAndConditions.setOnClickListener {
            val intent = Intent(this, TermsAndConditionsActivity::class.java)
            startActivity(intent)
        }
    }
}