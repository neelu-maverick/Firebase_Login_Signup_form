package com.example.firebase_login_signup_form.petsfolder.fishpackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentFishBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.petsfolder.dogspackage.DogsAdapter

class FishFragment : Fragment() {

    lateinit var fishBinding: FragmentFishBinding
    lateinit var fishAdapter: FishAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fishBinding = FragmentFishBinding.inflate(inflater, container, false)
        return fishBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        fishAdapter.submitList(data())
    }

    private fun initRecyclerView() {
        fishAdapter = FishAdapter()
        fishBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fishAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Catfish"))
        holder.add(PetsHelper("Cichlids"))
        holder.add(PetsHelper("Rainbow Fish"))
        holder.add(PetsHelper("Sunfish"))
        holder.add(PetsHelper("Angelfish"))
        holder.add(PetsHelper("Barbs"))
        holder.add(PetsHelper("Betta Fish"))
        holder.add(PetsHelper("Clown Loach"))
        holder.add(PetsHelper("Guppies"))
        holder.add(PetsHelper("Platies and Swordtails"))
        holder.add(PetsHelper("Rainbow Sharks"))
        holder.add(PetsHelper("Blenny"))
        holder.add(PetsHelper("Butterfly Fish"))
        holder.add(PetsHelper("Triggerfish"))

        return holder
    }


}