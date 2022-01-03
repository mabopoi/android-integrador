package com.example.notbored.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.example.notbored.R
import com.example.notbored.data.APIServiceSuggestion
import com.example.notbored.data.RetrofitService
import com.example.notbored.databinding.ActivitySuggestionBinding
import com.example.notbored.databinding.SuggestionToolbarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private var participants: String? = null
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participants = intent.getStringExtra("participants")
        type = intent.getStringExtra("type")

        val toolbarBinding = SuggestionToolbarBinding.inflate(layoutInflater)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = toolbarBinding.root

        type?.let {
            binding.tvCategory.visibility = View.INVISIBLE
            binding.ivCategory.visibility = View.INVISIBLE
            toolbarBinding.tvSuggestionTitle.text = type
        } ?: run {
            toolbarBinding.tvSuggestionTitle.text = getString(R.string.random)
        }

        toolbarBinding.btnGoBack.setOnClickListener { finish() }

        binding.btnTryAnother.setOnClickListener { getSuggestion() }

        getSuggestion()
    }

    private fun getSuggestion(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitService.instance.create(APIServiceSuggestion::class.java).getSuggestion(participants, type)
            val response = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    response?.let { //checks if response is null
                        it.error?.let { err -> //checks if there is an error
                            println(err)
                            binding.tvSuggestionName.text = err
                            binding.tvParticipants.visibility = View.INVISIBLE
                            binding.tvParticipantsTitle.visibility = View.INVISIBLE
                            binding.ivParticipants.visibility = View.INVISIBLE
                            binding.tvPrice.visibility = View.INVISIBLE
                            binding.tvPriceTitle.visibility = View.INVISIBLE
                            binding.ivPrice.visibility = View.INVISIBLE
                            binding.ivCategory.visibility = View.INVISIBLE
                            binding.tvCategory.visibility = View.INVISIBLE
                            err
                        } ?: run {
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
                } else {
                    binding.tvSuggestionName.text = getString(R.string.errorMsg)
                }
            }
        }
    }
}