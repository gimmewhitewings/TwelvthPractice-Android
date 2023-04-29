package com.example.twelvthpractice.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.MediaService
import com.example.twelvthpractice.databinding.FragmentServiceBinding

class ServiceFragment : Fragment() {

    private lateinit var binding: FragmentServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = Intent(context, MediaService::class.java)
        binding.apply {
            startButton.setOnClickListener {
                context?.startService(intent)
            }
            stopButton.setOnClickListener {
                context?.stopService(intent)
            }
        }
    }

}