package com.example.firebase_login_signup_form.petsfolder.horsepackage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentHorsesBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HorsesFragment : Fragment() {
    lateinit var horseBinding: FragmentHorsesBinding
    lateinit var horsesAdapter: HorsesAdapter
    lateinit var petDatabase: PetDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        horseBinding = FragmentHorsesBinding.inflate(inflater, container, false)
        return horseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        horsesAdapter.submitList(data())
        initDatabase()
    }

    private fun initRecyclerView() {
        horsesAdapter = HorsesAdapter()
        horseBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = horsesAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Arabian Horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("Frieian Horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("Mustang", R.drawable.germanshepherd))
        holder.add(PetsHelper("Shire Horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("Appaloosa", R.drawable.germanshepherd))
        holder.add(PetsHelper("Dutch Warmblood", R.drawable.germanshepherd))
        holder.add(PetsHelper("American Quarter Horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("Breton Horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("Halflinger", R.drawable.germanshepherd))
        holder.add(PetsHelper("Shetland pony", R.drawable.germanshepherd))
        holder.add(PetsHelper("Criolo", R.drawable.germanshepherd))
        holder.add(PetsHelper("Lipizzan", R.drawable.germanshepherd))
        holder.add(PetsHelper("Paso Fino", R.drawable.germanshepherd))
        holder.add(PetsHelper("Marwari horse", R.drawable.germanshepherd))
        holder.add(PetsHelper("American Saddlebred", R.drawable.germanshepherd))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
//            val names = petDataDao.insertAll(PetsHelper(data().toString()))
//            Log.d("NAMES","$names")
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}