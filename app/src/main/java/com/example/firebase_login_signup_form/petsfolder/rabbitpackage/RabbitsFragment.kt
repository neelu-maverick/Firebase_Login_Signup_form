package com.example.firebase_login_signup_form.petsfolder.rabbitpackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.FragmentRabbitsBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.roomdb.PetDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RabbitsFragment : Fragment() {
    lateinit var rabbitBinding: FragmentRabbitsBinding
    lateinit var rabbitAdapter: RabbitAdapter
    lateinit var petDatabase: PetDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        rabbitBinding = FragmentRabbitsBinding.inflate(inflater, container, false)
        return rabbitBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        rabbitAdapter.submitList(data())
        initDatabase()
    }

    private fun initRecyclerView() {
        rabbitAdapter = RabbitAdapter()
        rabbitBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rabbitAdapter
            setHasFixedSize(true)
        }
    }


    private fun data(): ArrayList<PetsHelper>? {
        val holder: ArrayList<PetsHelper> = ArrayList()
        holder.add(PetsHelper("Havana Rabbit", R.drawable.havanarabbit))
        holder.add(PetsHelper("Rex Rabbit", R.drawable.rexrabbit))
        holder.add(PetsHelper("Angora Rabbit", R.drawable.angorarabbit))
        holder.add(PetsHelper("Polish Rabbit", R.drawable.polishrabbit))
        holder.add(PetsHelper("Flemish Giant Rabbit", R.drawable.flamishgaintrabbit))
        holder.add(PetsHelper("Teddy dwarf", R.drawable.dwardrabbit))
        holder.add(PetsHelper("Plush lop", R.drawable.plushloprabbit))
        holder.add(PetsHelper("Lilac Rabbit", R.drawable.lilacrabbit))
        holder.add(PetsHelper("Swedish Hare", R.drawable.swedishrabbit))
        holder.add(PetsHelper("Czech Rabbit", R.drawable.czechrabbit))

        return holder
    }

    private fun initDatabase() {
        petDatabase = PetDatabase.getDatabase(requireContext())

        GlobalScope.launch {

            val petDataDao = petDatabase.petDataDao()
//           val names = petDataDao.insertAll(PetsHelper(data().toString()))
//            Log.d("NAMES","$names")
        }
        //Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show()
    }
}