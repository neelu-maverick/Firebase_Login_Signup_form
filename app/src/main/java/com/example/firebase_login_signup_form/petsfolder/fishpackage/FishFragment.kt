package com.example.firebase_login_signup_form.petsfolder.fishpackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentFishBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FishFragment : Fragment() {

    lateinit var fishBinding: FragmentFishBinding
    lateinit var fishAdapter: FishAdapter
    lateinit var petDatabase: PetDatabase

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
        initDatabase()
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
        holder.add(PetsHelper("Catfish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Cichlids", R.drawable.germanshepherd))
        holder.add(PetsHelper("Rainbow Fish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Sunfish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Angelfish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Barbs", R.drawable.germanshepherd))
        holder.add(PetsHelper("Betta Fish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Clown Loach", R.drawable.germanshepherd))
        holder.add(PetsHelper("Guppies", R.drawable.germanshepherd))
        holder.add(PetsHelper("Platies and Swordtails", R.drawable.germanshepherd))
        holder.add(PetsHelper("Rainbow Sharks", R.drawable.germanshepherd))
        holder.add(PetsHelper("Blenny", R.drawable.germanshepherd))
        holder.add(PetsHelper("Butterfly Fish", R.drawable.germanshepherd))
        holder.add(PetsHelper("Triggerfish", R.drawable.germanshepherd))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
//           val names = petDataDao.insertAll(PetsHelper(data().toString()))
//            Log.d("NAMES","$names")
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }

}