package com.example.shopapp.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.di.application.appComponent
import com.example.shopapp.ui.home.HomeFragmentDirections
import com.example.shopapp.ui.home.RecyclerAdapter
import com.example.shopapp.ui.static.Profile
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    lateinit var adapter: CartAdapter
    private var _binding: FragmentCartBinding? = null
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

        val cartViewModel =
            ViewModelProvider(this,vmFactory).get(CartViewModel::class.java)

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.itemView.layoutManager = LinearLayoutManager(binding.root.context)

        //adapter action on tap
        adapter = CartAdapter(glide) {pos,address ->
            val item = cartViewModel.getItems().value!![pos]
            val action =CartFragmentDirections.actionNavigationCartToWebFragment(address, item)
            findNavController().navigate(action)
            Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
        }
        binding.itemView.adapter = adapter
        //todo kostyl ebany
        if (Profile.cartList != cartViewModel.getItems().value)
            cartViewModel.getItems().value = Profile.cartList

        cartViewModel.getItems().observe(viewLifecycleOwner) {
            adapter.addToList(it)
            if (it.isNullOrEmpty())
                Toast.makeText(binding.root.context, "Nothing :(", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}