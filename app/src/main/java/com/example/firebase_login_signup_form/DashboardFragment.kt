package com.example.firebase_login_signup_form

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.example.firebase_login_signup_form.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    lateinit var fAuth: FirebaseAuth
    lateinit var dashboardFragBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        dashboardFragBinding =
            FragmentDashboardBinding.inflate(inflater, container, false)
        return dashboardFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fAuth = FirebaseAuth.getInstance()

        dashboardFragBinding.emailId.text = fAuth.currentUser?.email
        dashboardFragBinding.uidhome.text = fAuth.currentUser?.uid
        dashboardFragBinding.uiname.text = fAuth.currentUser?.displayName
        dashboardFragBinding.imageview.setImageURI(fAuth.currentUser?.photoUrl)

        dashboardFragBinding.galleryTextId.setOnClickListener {
            Toast.makeText(context, "Opening..", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dashboardFragment_to_categoriesViewFragment)
        }

        dashboardFragBinding.logoutBtn.setOnClickListener {
            fAuth.signOut()
            findNavController().navigate(R.id.action_dashboardFragment_to_LoginFragment)
            Toast.makeText(context, "Successfully Logged Out", Toast.LENGTH_LONG).show()

        }
    }
}