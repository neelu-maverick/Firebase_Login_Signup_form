package com.example.firebase_login_signup_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebase_login_signup_form.adapters.CategoriesAdapter
import com.example.firebase_login_signup_form.databinding.FragmentAddCategoryFaBinding
import com.example.firebase_login_signup_form.databinding.FragmentCategoriesViewBinding
import com.example.firebase_login_signup_form.dataclasses.CategoriesHelper
import com.example.firebase_login_signup_form.floatingaction.AddCategoryFAFragment

class CategoriesViewFragment : Fragment(), OnClickListener {

    lateinit var categoriesViewBinding: FragmentCategoriesViewBinding
    lateinit var categoriesAdapter: CategoriesAdapter
    var fabVisible = false
    lateinit var categoryList: ArrayList<CategoriesHelper>

    lateinit var addCategoryFaBinding: FragmentAddCategoryFaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        categoriesViewBinding = FragmentCategoriesViewBinding.inflate(inflater, container, false)

        addCategoryFaBinding = FragmentAddCategoryFaBinding.inflate(inflater, container, false)

        return categoriesViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryList = ArrayList()
        initRecyclerView()
        categoriesAdapter.submitList(data())
        initFAB()

    }


    private fun initRecyclerView() {
        categoriesAdapter =
            CategoriesAdapter(this, categoryList)
        categoriesViewBinding.galleryRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            itemAnimator = DefaultItemAnimator()
            adapter = categoriesAdapter
            setHasFixedSize(true)
            // scrollToPosition(categoryList.size - 1)
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
        holder.add(CategoriesHelper("Videos", R.drawable.ic_video_library))

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
            7 -> findNavController().navigate(R.id.action_categoriesViewFragment_to_exoVideoPlayerFragment)
            else -> Toast.makeText(context, "No fragment found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFAB() {

        fabVisible = false
        categoriesViewBinding.addFabHome.setOnClickListener {
            if (!fabVisible) {
                categoriesViewBinding.fabCreate.show()
                categoriesViewBinding.fabModify.show()
                categoriesViewBinding.fabDelete.show()

                categoriesViewBinding.fabCreate.visibility = View.VISIBLE
                categoriesViewBinding.fabModify.visibility = View.VISIBLE
                categoriesViewBinding.fabDelete.visibility = View.VISIBLE

                categoriesViewBinding.addFabHome.setImageDrawable(resources.getDrawable(R.drawable.ic_cancel))

                fabVisible = true
            } else {
                categoriesViewBinding.fabCreate.hide()
                categoriesViewBinding.fabModify.hide()
                categoriesViewBinding.fabDelete.hide()

                categoriesViewBinding.fabCreate.visibility = View.GONE
                categoriesViewBinding.fabModify.visibility = View.GONE
                categoriesViewBinding.fabDelete.visibility = View.GONE

                categoriesViewBinding.addFabHome.setImageDrawable(resources.getDrawable(R.drawable.ic_add))
                fabVisible = false
            }
        }

        categoriesViewBinding.fabCreate.setOnClickListener {

            Toast.makeText(context, "Create", Toast.LENGTH_SHORT).show()

            AddCategoryFAFragment().show(parentFragmentManager, "MYDialogBox")
            // findNavController().navigate(R.id.action_categoriesViewFragment_to_addCategoryFAFragment)

        }

        categoriesViewBinding.fabModify.setOnClickListener {
            Toast.makeText(context, "Modify", Toast.LENGTH_SHORT).show()
        }
        categoriesViewBinding.fabDelete.setOnClickListener {
            Toast.makeText(context, "Select to delete", Toast.LENGTH_SHORT).show()
        }
    }

}

