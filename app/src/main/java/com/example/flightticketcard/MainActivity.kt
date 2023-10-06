package com.example.flightticketcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightticketcard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ticket.setOnClickListener {
            binding.ticket.ticketBackgroundColor = R.color.bg
        }
    }
}