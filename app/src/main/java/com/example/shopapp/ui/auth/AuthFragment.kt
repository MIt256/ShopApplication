package com.example.shopapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentAuthBinding
import com.example.shopapp.ui.static.FirebaseProfile
import com.example.shopapp.ui.static.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val reference by lazy { DB.getReference() }
    private val auth by lazy { Firebase.auth }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        setListener()
        return binding.root
    }



    private fun checkData(login: String, password: String): Boolean {
        if (login.isNotEmpty() && password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(login)
                .matches()
        )
            return true
        else
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun setListener() {
        binding.buttonReg.setOnClickListener {
            val login = binding.name.text.toString()
            val password = binding.password.text.toString()
            if (checkData(login, password)) {
                auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Profile.email = login
                        reference.child(login.hashCode().toString()).child("profile")
                            .setValue(Profile.getFirebaseProfile())
                        val bundle = Bundle()
                        bundle.putString("hash", login.hashCode().toString())
                        Navigation
                            .findNavController(binding.root)
                            .navigate(
                                R.id.action_authFragment_to_navigation_settings,
                                bundle,
                                null
                            )
                    } else
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.buttonAuth.setOnClickListener {
            val login = binding.name.text.toString()
            val password = binding.password.text.toString()
            if (checkData(login, password)) {
                auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //get profile
                        reference.child(login.hashCode().toString()).child("profile").get().addOnSuccessListener {
                           Profile.setProfile(it.getValue(FirebaseProfile::class.java)!!)
                        }.addOnFailureListener{
                            Log.e("firebase", "Error getting data", it)
                        }

                        val bundle = Bundle()
                        bundle.putString("hash", login.hashCode().toString())
                        Navigation
                            .findNavController(binding.root)
                            .navigate(
                                R.id.action_authFragment_to_navigation_settings,
                                bundle,
                                null
                            )
                    } else
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}