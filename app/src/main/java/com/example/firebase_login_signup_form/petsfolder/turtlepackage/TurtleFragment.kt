package com.example.firebase_login_signup_form.petsfolder.turtlepackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentTurtleBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.petsfolder.rabbitpackage.RabbitAdapter

class TurtleFragment : Fragment() {
    lateinit var turtleBinding: FragmentTurtleBinding
    lateinit var turtleAdapter: TurtleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        turtleBinding = FragmentTurtleBinding.inflate(inflater, container, false)
        return turtleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        turtleAdapter.submitList(data())
    }

    private fun initRecyclerView() {
        turtleAdapter = TurtleAdapter()
        turtleBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = turtleAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Red-Eared Slider"))
        holder.add(PetsHelper("Mississippi Map Turtle"))
        holder.add(PetsHelper("Spotted Turtle"))
        holder.add(PetsHelper("Reev's Turtle"))
        holder.add(PetsHelper("Wood Turtle"))
        holder.add(PetsHelper("Common Musk Turtle"))
        holder.add(PetsHelper("African Sidenack Turtle"))
        holder.add(PetsHelper("Eastern box Turtle"))

        return holder
    }
}