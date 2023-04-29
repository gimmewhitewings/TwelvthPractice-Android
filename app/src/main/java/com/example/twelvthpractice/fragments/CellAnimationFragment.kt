package com.example.twelvthpractice.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.R
import com.example.twelvthpractice.databinding.FragmentCellAnimationBinding


class CellAnimationFragment : Fragment() {

    private lateinit var binding: FragmentCellAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCellAnimationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cellAnimView.setBackgroundResource(R.drawable.flower_animation)
        val frameAnimation = binding.cellAnimView.background as AnimationDrawable
        frameAnimation.start()
    }
}