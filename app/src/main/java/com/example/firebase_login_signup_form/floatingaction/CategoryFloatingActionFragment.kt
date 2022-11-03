package com.example.firebase_login_signup_form.floatingaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentCategoryFloatingActionBinding

class CategoryFloatingActionFragment : Fragment() {

    lateinit var floatingCategoryBinding : FragmentCategoryFloatingActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        floatingCategoryBinding = FragmentCategoryFloatingActionBinding.inflate(inflater, container, false)
        return floatingCategoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}