package com.example.running

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.running.databinding.ActivityRunningBinding
import kotlin.math.roundToInt

class RunningActivity : AppCompatActivity() {
    lateinit var binding: ActivityRunningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRunningBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val circularTrack = intent.getBooleanExtra("CircularTrack", false)
        val km = intent.getDoubleExtra("meter",0.0) / 1000.0
        val kg = intent.getDoubleExtra("kg", 70.0)
        var distance = 0.0
        binding.lap.setOnClickListener {
            distance += if (circularTrack) {
                km * 2
            } else {
                km
            }
            binding.kilometer.text = "Kilometer: " + String.format("%.3f",distance)
            binding.Calories.text = "Calories: " + calculateCalories(distance, kg).toString()
        }
        binding.halfLap.setOnClickListener {
            distance += if (circularTrack) {
                km
            } else {
                km / 2
            }
            binding.kilometer.text = "Kilometer: " + String.format("%.3f",distance)
            binding.Calories.text = "Calories: " + calculateCalories(distance, kg).toString()
        }
    }
    fun calculateCalories(distance: Double, weight: Double, pace: String = "moderate"): Int {
        val metValues = mapOf(
            "slow" to 8,
            "moderate" to 10,
            "fast" to 12,
            "very_fast" to 15
        )

        val met = metValues[pace] ?: 10
        return (met * weight * distance * 0.1).roundToInt()
    }
}