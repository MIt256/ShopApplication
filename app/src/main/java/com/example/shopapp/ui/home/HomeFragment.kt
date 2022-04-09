package com.example.shopapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.di.application.appComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    lateinit var adapter: RecyclerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.itemView.layoutManager = LinearLayoutManager(binding.root.context)

        val homeViewModel =
            ViewModelProvider( this, vmFactory).get(HomeViewModel::class.java)

        homeViewModel.getText().observe(viewLifecycleOwner) {
            adapter.addToList(it.findItemsByKeywordsResponse[0].searchResult[0].items)
        }
        //move from here
        homeViewModel.getList()

        adapter = RecyclerAdapter(glide) {_,address ->
            val bundle = Bundle()
                bundle.putString("address", address)

            Navigation
                .findNavController(binding.root)
                .navigate(R.id.action_navigation_home_to_webFragment,bundle)

            Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
        }
        binding.itemView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}