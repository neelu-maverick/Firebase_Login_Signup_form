package com.example.firebase_login_signup_form.petsfolder.birdspackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.OnClickListener
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentBirdsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BirdsFragment : Fragment(), OnClickListener {
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
        birdsAdapter = BirdsAdapter(this)
        birdsBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = birdsAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Parrots", R.drawable.parrot))
        holder.add(PetsHelper("Cockatiel", R.drawable.cockatiel))
        holder.add(PetsHelper("Cockatoo", R.drawable.cockatoo))
        holder.add(PetsHelper("Dove", R.drawable.dove))
        holder.add(PetsHelper("Parrotlet", R.drawable.parrotlet))
        holder.add(PetsHelper("Budgies", R.drawable.budgies))
        holder.add(PetsHelper("Lovebirds", R.drawable.lovebird))
        holder.add(PetsHelper("Zebra Finch", R.drawable.zebrafinch))
        holder.add(PetsHelper("Macaw Birds", R.drawable.macaw))
        holder.add(PetsHelper("Canary", R.drawable.canary))

        return holder
    }

    override fun onClick(position: Int) {
        findNavController().navigate(R.id.action_birdsFragment_to_pictureFragment)
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
            // val names = petDataDao.insertAll(PetsHelper(data().toString()))
            //Log.d("NAMES", "$names")
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}