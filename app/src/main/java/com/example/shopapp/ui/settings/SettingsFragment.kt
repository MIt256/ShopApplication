package com.example.shopapp.ui.settings

import android.R
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.shopapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    var languages = arrayOf("USD", "EUR", "RUB", "BYR", "CNY","UAH")
    val NEW_SPINNER_ID = 1
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1) }

        var aa = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(R.layout.simple_spinner_item)

        with(binding.spinner)
        {
            adapter = aa
            setSelection(0, false)
            prompt = "Select currency"
        }




        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            binding.imageView.setImageURI(data.data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}