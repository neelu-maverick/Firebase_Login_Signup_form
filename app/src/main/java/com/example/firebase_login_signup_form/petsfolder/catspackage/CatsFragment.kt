package com.example.firebase_login_signup_form.petsfolder.catspackage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentCatsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CatsFragment : Fragment() {

    lateinit var catsBinding: FragmentCatsBinding
    lateinit var catsAdapter: CatsAdapter
    lateinit var petDatabase: PetDatabase

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
        initDatabase()
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
        holder.add(PetsHelper("Persian cats", R.drawable.persiancat))
        holder.add(PetsHelper("Scottish fold", R.drawable.scottishfold))
        holder.add(PetsHelper("American Shorthair", R.drawable.americanshorthair))
        holder.add(PetsHelper("Siamese cat", R.drawable.siamesecat))
        holder.add(PetsHelper("Maine coon", R.drawable.mainecoon))
        holder.add(PetsHelper("Birman", R.drawable.birman))
        holder.add(PetsHelper("Bombay cat", R.drawable.bombaycat))
        holder.add(PetsHelper("Russian Blue", R.drawable.russianblue))
        holder.add(PetsHelper("Turkish angora", R.drawable.turkishangora))
        holder.add(PetsHelper("Japanese Bobtail", R.drawable.japanesebobtail))
        holder.add(PetsHelper("Himalayan Cat", R.drawable.himalayancat))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
//            val names = petDataDao.insertAll(PetsHelper(data().toString()))
//            Log.d("NAMES", "$names")

        }
        //Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}