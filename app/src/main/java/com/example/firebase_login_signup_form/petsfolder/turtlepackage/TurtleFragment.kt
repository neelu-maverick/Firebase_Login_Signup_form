package com.example.firebase_login_signup_form.petsfolder.turtlepackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentTurtleBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TurtleFragment : Fragment() {
    lateinit var turtleBinding: FragmentTurtleBinding
    lateinit var turtleAdapter: TurtleAdapter
    lateinit var petDatabase: PetDatabase

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
        initDatabase()
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
        holder.add(PetsHelper("Red-Eared Slider", R.drawable.redearedslider))
        holder.add(PetsHelper("Mississippi Map Turtle", R.drawable.mississippimap))
        holder.add(PetsHelper("Spotted Turtle", R.drawable.spottedturtle))
        holder.add(PetsHelper("Reev's Turtle", R.drawable.reev_s_turtle))
        holder.add(PetsHelper("Wood Turtle", R.drawable.woodturtle))
        holder.add(PetsHelper("Common Musk Turtle", R.drawable.commonmusk))
        holder.add(PetsHelper("African Sidenack Turtle", R.drawable.africansideneck))
        holder.add(PetsHelper("Eastern box Turtle", R.drawable.easternbox))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
//           val names =  petDataDao.insertAll(PetsHelper(data().toString()))
//            Log.d("NAMES","$names")
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}