package com.example.firebase_login_signup_form.petsfolder.birdspackage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.databinding.FragmentBirdsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BirdsFragment : Fragment() {
    lateinit var birdsBinding: FragmentBirdsBinding
    lateinit var birdsAdapter: BirdsAdapter
    lateinit var petDatabase: PetDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        birdsBinding = FragmentBirdsBinding.inflate(inflater, container, false)
        return birdsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        birdsAdapter.submitList(data())
        initDatabase()
    }

    private fun initRecyclerView() {
        birdsAdapter = BirdsAdapter()
        birdsBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = birdsAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Parrots"))
        holder.add(PetsHelper("Cockatiel"))
        holder.add(PetsHelper("Cockatoo"))
        holder.add(PetsHelper("Dove"))
        holder.add(PetsHelper("Parrotlet"))
        holder.add(PetsHelper("Budgies"))
        holder.add(PetsHelper("Lovebirds"))
        holder.add(PetsHelper("Zebra Finch"))
        holder.add(PetsHelper("Macaw Birds"))
        holder.add(PetsHelper("Canary"))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
           val names = petDataDao.insertAll(PetsHelper(data().toString()))
            Log.d("NAMES","$names")
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}