package com.example.getjokes

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.getjokes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnJoke.setOnClickListener {
            binding.idLoadingPB.visibility = View.VISIBLE
            // Call the getjokes() method of the ApiCall class,
            // passing a callback function as a parameter.
            ApiCalls().getjokes(this) { jokes ->
                // Set the text of the text view to the
                // joke value returned by the API response.
                binding.tvJoke.text = jokes.value
                // hide the progress bar
                binding.idLoadingPB.visibility = View.GONE
            }
        }
    }
}
