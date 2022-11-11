package com.example.firebase_login_signup_form.petsfolder.dogspackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentDogsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DogsFragment : Fragment() {

    lateinit var dogsBinding: FragmentDogsBinding
    lateinit var dogsAdapter: DogsAdapter
    lateinit var petDatabase: PetDatabase

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
        initDatabase()
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
        holder.add(PetsHelper("German Shepherd", R.drawable.germanshepherd))
        holder.add(PetsHelper("Bulldog", R.drawable.bulldog))
        holder.add(PetsHelper("Labrador Retriever", R.drawable.labrador))
        holder.add(PetsHelper("Siberian Husky", R.drawable.siberianhusky))
        holder.add(PetsHelper("Chow-chow", R.drawable.chowchow))
        holder.add(PetsHelper("Dachshund", R.drawable.dachshund))
        holder.add(PetsHelper("Great Dane", R.drawable.greatdane))
        holder.add(PetsHelper("Pomeranian", R.drawable.pomeranian))
        holder.add(PetsHelper("American Bully", R.drawable.americanbully))
        holder.add(PetsHelper("Maltipoo", R.drawable.maltipoo))
        holder.add(PetsHelper("Dalmatian", R.drawable.dalmatian))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
            //val names = petDataDao.insertAll(petsHelper = PetsHelper(data().toString()))
            //  Log.d("NAMES","$names")

        }
       // Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }

}