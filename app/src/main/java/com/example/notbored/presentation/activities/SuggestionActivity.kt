package com.example.notbored.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notbored.R
import com.example.notbored.data.APIServiceSuggestion
import com.example.notbored.data.RetrofitService
import com.example.notbored.databinding.ActivitySuggestionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTryAnother.setOnClickListener { getSuggestion() }

        getSuggestion()
    }

    private fun getSuggestion(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitService.instance.create(APIServiceSuggestion::class.java).getSuggestion()
            val response = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    response?.let {
                        binding.tvSuggestionName.text = response.activity
                        binding.tvParticipants.text =  response.participants.toString()
                        binding.tvPrice.text = when {
                            response.price == 0.0 -> getString(R.string.free)
                            response.price > 0.0 && response.price <= 0.3 -> getString(R.string.low)
                            response.price > 0.3 && response.price <= 0.6 -> getString(R.string.medium)
                            response.price > 0.6 -> getString(R.string.high)
                            else -> getString(R.string.free)
                        }
                        binding.tvCategory.text = response.type
                    }
                }
            }
        }
    }
}