package com.example.firebase_login_signup_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase_login_signup_form.adapters.CategoriesAdapter
import com.example.firebase_login_signup_form.databinding.FragmentCategoriesViewBinding
import com.example.firebase_login_signup_form.dataclasses.CategoriesHelper
import com.example.firebase_login_signup_form.petsfolder.birdspackage.BirdsFragment
import com.example.firebase_login_signup_form.petsfolder.catspackage.CatsFragment
import com.example.firebase_login_signup_form.petsfolder.dogspackage.DogsFragment
import com.example.firebase_login_signup_form.petsfolder.fishpackage.FishFragment
import com.example.firebase_login_signup_form.petsfolder.horsepackage.HorsesFragment
import com.example.firebase_login_signup_form.petsfolder.rabbitpackage.RabbitsFragment
import com.example.firebase_login_signup_form.petsfolder.turtlepackage.TurtleFragment

class CategoriesViewFragment : Fragment(), OnClickListener {

    lateinit var categoriesViewBinding: FragmentCategoriesViewBinding
    lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        categoriesViewBinding = FragmentCategoriesViewBinding.inflate(inflater, container, false)
        return categoriesViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        categoriesAdapter.submitList(data())
    }


    private fun initRecyclerView() {
        categoriesAdapter = CategoriesAdapter(this)
        //categoriesAdapter = CategoriesAdapter()
        categoriesViewBinding.galleryRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoriesAdapter
            setHasFixedSize(true)
        }
    }

    fun data(): ArrayList<CategoriesHelper> {
        val holder: ArrayList<CategoriesHelper> = ArrayList()
        holder.add(CategoriesHelper("Dogs", R.drawable.dogs))
        holder.add(CategoriesHelper("Birds", R.drawable.birds))
        holder.add(CategoriesHelper("Cats", R.drawable.cats))
        holder.add(CategoriesHelper("Rabbits", R.drawable.rabbits))
        holder.add(CategoriesHelper("Horses", R.drawable.horses))
        holder.add(CategoriesHelper("Turtles", R.drawable.turtls))
        holder.add(CategoriesHelper("Fish", R.drawable.fish))

        return holder
    }

    override fun onClick(position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        when (position) {
            0 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_dogsFragment)
            1 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_birdsFragment)
            2 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_catsFragment)
            3 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_rabbitsFragment)
            4 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_horsesFragment)
            5 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_turtleFragment)
            6 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_fishFragment)
            else -> Toast.makeText(context, "No fragment found", Toast.LENGTH_SHORT).show()
        }

    }
}