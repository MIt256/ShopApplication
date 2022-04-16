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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopapp.databinding.FragmentSettingsBinding
import com.example.shopapp.ui.home.HomeFragmentDirections
import com.example.shopapp.ui.static.Profile


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
        //pic
        binding.imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1) }
        //spinner
        var aa = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(R.layout.simple_spinner_item)

        with(binding.spinner)
        {
            adapter = aa
            setSelection(0, false)
            prompt = "Select currency"
        }
        binding.imageView.setImageURI(Profile.image)

        binding.saveBtn.setOnClickListener {
            Profile.currency = binding.spinner.selectedItem.toString()
            Profile.firstName = binding.editTextFirstName.text.toString()
            Profile.secondName = binding.editTextMiddleName.text.toString()
            Profile.lastName = binding.editTextLastName.text.toString()

            Profile.theme = binding.themeChanger.isChecked.toString()

            Toast.makeText(context,"Success saved",Toast.LENGTH_SHORT).show()
        }

        binding.accountChange.setOnClickListener {
            val action = SettingsFragmentDirections.actionNavigationSettingsToAuthFragment2()
            findNavController().navigate(action)
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            binding.imageView.setImageURI(data.data)
            Profile.image = data.data
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}