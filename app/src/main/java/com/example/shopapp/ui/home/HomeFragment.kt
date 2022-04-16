package com.example.shopapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.di.application.appComponent
import com.example.shopapp.ui.model.AppItem
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

        //move result to adapter
        homeViewModel.getAllItems().observe(viewLifecycleOwner) {
            homeViewModel.setCategories()
            homeViewModel.setSelectedItems(0)
        }
        //observe selection
        homeViewModel.getSelectedItems().observe(viewLifecycleOwner) {
            adapter.addToList(it)
            if (it.isNullOrEmpty())
                Toast.makeText(binding.root.context, "Nothing :(", Toast.LENGTH_SHORT).show()
        }

        //after set categories
        homeViewModel.getCategories().observe(viewLifecycleOwner) {
            //category
            if (!it.isNullOrEmpty() && !(homeViewModel.getAllItems().value.isNullOrEmpty())) {
                val aa = ArrayAdapter(
                    binding.root.context, R.layout.spinner_item,
                    it
                )
                aa.setDropDownViewResource(R.layout.spinner_item)

                with(binding.spinnerCategory)
                {
                    isVisible = true
                    adapter = aa
                    setSelection(0, false)
                    prompt = "Select category"
                }
            } else {binding.spinnerCategory.isVisible = false}
        }

        //categories click
        binding.spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                homeViewModel.setSelectedItems(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }

        //adapter action on tap
        adapter = RecyclerAdapter(glide) {pos,address ->
            val item = homeViewModel.getSelectedItems().value!![pos]
            val action = HomeFragmentDirections.actionNavigationHomeToWebFragment3(address, item)

                findNavController().navigate(action)

            Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
        }
        binding.itemView.adapter = adapter

        //search
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //move from here
                homeViewModel.getList(binding.searchView.query.toString())
                return false
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}