package com.example.cointrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cointrack.databinding.ActivityMainBinding
import com.example.cointrack.databinding.FragmentCoinListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CoinListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinListFragment : Fragment(), CoinListAdapterDelegate {
    private val viewModel by viewModel<CoinListViewModel>()
    private lateinit var binding: FragmentCoinListBinding

    private lateinit var adapter: CoinListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinListBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieList.observe(viewLifecycleOwner) {
            Log.e("Got Data", it.size.toString())
            binding.rvCoinList.adapter = CoinListAdapter(it, this)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                // show loading view
            } else {
                // Hide loading view
            }
        }

        viewModel.getAllMovies()

    }

    override fun onCoinSelected(coin: CoinListResponse.CoinListResponseItem) {
       findNavController().navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailsFragment(coin))
    }
}