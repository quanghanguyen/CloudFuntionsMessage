package com.example.cloudfunctionsmessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cloudfunctionsmessage.databinding.ActivityMainBinding
import com.example.cloudfunctionsmessage.firebaseconnection.FireBaseMessaging
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FireBaseMessaging.firebaseMessaging.subscribeToTopic("android")
        initEvents()
        initObserve()
    }

    private fun initObserve() {
        mainViewModel.saveResult.observe(this) {result ->
            when (result) {
                is MainViewModel.SaveResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    binding.etTitle.text.clear()
                    binding.etAuthor.text.clear()
                }
                is MainViewModel.SaveResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val author = binding.etAuthor.text.toString()
            mainViewModel.save(title, author)
        }
    }
}