package com.example.shopapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.shopapp.databinding.FragmentWebBinding
import com.example.shopapp.di.application.appComponent
import javax.inject.Inject


class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebBinding.inflate(inflater, container, false)

        val args : WebFragmentArgs by navArgs()

        binding.webBrowser.loadUrl(args.address)
        binding.webBrowser.settings.javaScriptEnabled = true
        binding.webBrowser.webViewClient = WebViewClient()

        binding.addToCart.setOnClickListener {

        //todo add to cart
          //  args.item
           // val item = requireArguments().getSerializable("item") as Item
            Toast.makeText(binding.root.context,
                "${args.item.title} Success added to cart",
                Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}