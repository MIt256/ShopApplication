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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.di.application.appComponent
import com.example.shopapp.model.findingApi.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        homeViewModel.getResult().observe(viewLifecycleOwner) {
           adapter.addToList(it.findItemsByKeywordsResponse[0].searchResult[0].items)
        }
        //after set categories
        homeViewModel.getCategories().observe(viewLifecycleOwner) {
            //category

            var aa = ArrayAdapter(binding.root.context, R.layout.spinner_item,
                homeViewModel.getCategories().value!!)
            aa.setDropDownViewResource(R.layout.spinner_item)

            with(binding.spinnerCategory)
            {
                isVisible = true
                adapter = aa
                setSelection(0, false)
                prompt = "Select category"
            }
        }

        binding.spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if (homeViewModel.getCategories().value!![position] != "ALL") {
                    CoroutineScope(Dispatchers.Default).launch {
                        var categoryItems = ArrayList<Item>()
                        val allItems =
                            homeViewModel.getResult().value!!.findItemsByKeywordsResponse[0].searchResult[0].items
                        allItems.forEach {
                            if (it.primaryCategory[0].categoryName[0] == homeViewModel.getCategories().value!![position]) categoryItems.add(
                                it
                            )
                        }
                        withContext(Dispatchers.Main) { adapter.addToList(categoryItems) }
                    }
                } else
                    adapter.addToList(homeViewModel.getResult().value!!.findItemsByKeywordsResponse[0].searchResult[0].items)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {// write code to perform some action
             }

        }

        //adapter action on tap
        adapter = RecyclerAdapter(glide) {_,address ->
            val bundle = Bundle()
                bundle.putString("address", address)

            Navigation
                .findNavController(binding.root)
                .navigate(R.id.action_navigation_home_to_webFragment,bundle)

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