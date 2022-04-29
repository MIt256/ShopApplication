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
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.shopapp.databinding.FragmentSettingsBinding
import com.example.shopapp.ui.auth.DB
import com.example.shopapp.ui.home.HomeFragmentDirections
import com.example.shopapp.ui.static.CurrencyTypes
import com.example.shopapp.ui.static.Profile
import javax.inject.Inject


class SettingsFragment : Fragment() {



    private var _binding: FragmentSettingsBinding? = null

    val NEW_SPINNER_ID = 1
    private val binding get() = _binding!!

    private val reference by lazy { DB.getReference() }

//    @Inject
//    lateinit var glide: RequestManager

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
        var aa = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, CurrencyTypes.values())
        aa.setDropDownViewResource(R.layout.simple_spinner_item)

        with(binding.spinner)
        {
            adapter = aa
            setSelection(0, false)
            prompt = "Select currency"
        }


        binding.saveBtn.setOnClickListener {
            Profile.currency = binding.spinner.selectedItem.toString()
            Profile.firstName = binding.editTextFirstName.text.toString()
            Profile.secondName = binding.editTextMiddleName.text.toString()
            Profile.lastName = binding.editTextLastName.text.toString()

            Profile.theme = binding.themeChanger.isChecked.toString()
            //todo change?
            reference.child(Profile.email.hashCode().toString()).child("profile")
                .setValue(Profile.getFirebaseProfile())

            Toast.makeText(context,"Success saved",Toast.LENGTH_SHORT).show()


        }

        binding.themeChanger.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                setTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                setTheme(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.accountChange.setOnClickListener {
            val action = SettingsFragmentDirections.actionNavigationSettingsToAuthFragment2()
            findNavController().navigate(action)
        }

        if (Profile.image != "")
           Glide.with(root).load(Profile.image)
                .into(binding.imageView)

        return root
    }
    private fun setTheme(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            binding.imageView.setImageURI(data.data)
            Profile.image = data.data.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}