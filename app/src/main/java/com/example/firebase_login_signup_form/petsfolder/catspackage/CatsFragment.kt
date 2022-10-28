package com.example.firebase_login_signup_form.petsfolder.catspackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.databinding.FragmentCatsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.petsfolder.birdspackage.BirdsAdapter

class CatsFragment : Fragment() {

    lateinit var catsBinding: FragmentCatsBinding
    lateinit var catsAdapter: CatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        catsBinding = FragmentCatsBinding.inflate(inflater, container, false)
        return catsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        catsAdapter.submitList(data())
    }

    private fun initRecyclerView() {
        catsAdapter = CatsAdapter()
        catsBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catsAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Persian cats"))
        holder.add(PetsHelper("Scottish fold"))
        holder.add(PetsHelper("American Shorthair"))
        holder.add(PetsHelper("Siamese cat"))
        holder.add(PetsHelper("Maine coon"))
        holder.add(PetsHelper("Birman"))
        holder.add(PetsHelper("Bombay cat"))
        holder.add(PetsHelper("Russian Blue"))
        holder.add(PetsHelper("Turkish angora"))
        holder.add(PetsHelper("Japanese Bobtail"))
        holder.add(PetsHelper("Himalayan Cat"))

        return holder
    }
}