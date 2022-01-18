package com.example.androidroomdemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidroomdemoapp.R
import com.example.androidroomdemoapp.databinding.ActivityMainBinding
import com.example.androidroomdemoapp.db.SubscriberDatabase
import com.example.androidroomdemoapp.db.SubscriberRepository
import com.example.androidroomdemoapp.viewmodel.SubscriberViewModel
import com.example.androidroomdemoapp.viewmodel.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this
    }
}