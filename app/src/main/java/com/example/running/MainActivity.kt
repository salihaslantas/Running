package com.example.running

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.running.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.submit.setOnClickListener {
            val CircularTrack = binding.circularTrack.isActivated
            val meter = binding.meterEditText.text.toString().toDouble()
            if (meter != 0.0) {
                val intent = Intent(this@MainActivity, RunningActivity::class.java)
                intent.putExtra("CircularTrack",CircularTrack)
                intent.putExtra("meter", meter)
                startActivity(intent)
            }
        }
    }
}