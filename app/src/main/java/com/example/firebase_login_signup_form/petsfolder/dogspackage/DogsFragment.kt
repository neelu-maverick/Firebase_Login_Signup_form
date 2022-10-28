package com.example.firebase_login_signup_form.petsfolder.dogspackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.databinding.FragmentDogsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class DogsFragment : Fragment() {

    lateinit var dogsBinding: FragmentDogsBinding
    lateinit var dogsAdapter: DogsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        dogsBinding = FragmentDogsBinding.inflate(inflater, container, false)
        return dogsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        dogsAdapter.submitList(data())
    }

    private fun initRecyclerView() {
        dogsAdapter = DogsAdapter()
        dogsBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("German Shepherd"))
        holder.add(PetsHelper("Bulldog"))
        holder.add(PetsHelper("Labrador Retriever"))
        holder.add(PetsHelper("Siberian Husky"))
        holder.add(PetsHelper("Chow-chow"))
        holder.add(PetsHelper("Dachshund"))
        holder.add(PetsHelper("Great Dane"))
        holder.add(PetsHelper("Pomeranian"))
        holder.add(PetsHelper("American Bully"))
        holder.add(PetsHelper("Maltipoo"))
        holder.add(PetsHelper("Dalmatian"))

        return holder
    }


}