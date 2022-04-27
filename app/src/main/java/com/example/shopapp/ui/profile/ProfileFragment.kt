package com.example.shopapp.ui.profile

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.shopapp.databinding.FragmentProfileBinding
import com.example.shopapp.di.application.appComponent
import com.example.shopapp.ui.static.Profile
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class ProfileFragment : Fragment(), OnMapReadyCallback{

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private var _binding: FragmentProfileBinding? = null
    private lateinit var nmap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this,vmFactory).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding){
            if (Profile.image != "")
                glide.load(Profile.image)
                    .into(binding.imageView)
            
            firstName.text = Profile.firstName
            secondName.text = Profile.secondName
            lastName.text = Profile.lastName

            email.text = Profile.email
        }

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        fusedLocationClient =LocationServices.getFusedLocationProviderClient(requireActivity())

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        nmap = googleMap
        nmap.setMinZoomPreference(12F)
        setUpMap()
    }

    private fun setUpMap() {
        if( (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)
        }
            nmap.isMyLocationEnabled = true
            activity?.let { location ->
                fusedLocationClient.lastLocation.addOnSuccessListener(location) {
                    if (it != null) {
                        lastLocation = it
                        var currentLatLong = LatLng(it.latitude, it.longitude)
                        nmap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 14f))
                    }
                }
            }

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
