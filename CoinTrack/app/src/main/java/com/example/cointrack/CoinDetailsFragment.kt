package com.example.cointrack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cointrack.databinding.FragmentCoinDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CoinDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinDetailsFragment : Fragment() {
    lateinit var binding: FragmentCoinDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinDetailsBinding.inflate(layoutInflater)
        return binding.root
    }
}