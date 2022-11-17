package com.example.firebase_login_signup_form.floatingaction

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.firebase_login_signup_form.OnClickListener
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.adapters.CategoriesAdapter
import com.example.firebase_login_signup_form.databinding.FragmentAddCategoryFaBinding
import com.example.firebase_login_signup_form.databinding.FragmentCategoriesViewBinding
import com.example.firebase_login_signup_form.dataclasses.CategoriesHelper

class AddCategoryFAFragment : DialogFragment(), OnClickListener {

    lateinit var addCategoryFABinding: FragmentAddCategoryFaBinding
    lateinit var categoriesAdapter: CategoriesAdapter
    var categoryList : ArrayList<CategoriesHelper> = ArrayList()
    lateinit var categoriesViewBinding: FragmentCategoriesViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        addCategoryFABinding =
            FragmentAddCategoryFaBinding.inflate(inflater, container, false)

        categoriesViewBinding = FragmentCategoriesViewBinding.inflate(inflater, container, false)

        return addCategoryFABinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getResult = registerForActivityResult<String, Uri>(
            ActivityResultContracts.GetContent()
        ) { result ->
            addCategoryFABinding.displayImageId.setImageURI(result)
        }

        addCategoryFABinding.icCamera.setOnClickListener {
            getResult.launch("image/*")
        }

        addCategoryFABinding.actionCategBtn.setOnClickListener {

            var name: String = ""

            if (addCategoryFABinding.nameTV.text.toString() != "") {
                name = addCategoryFABinding.nameTV.text.toString()
            } else {
                addCategoryFABinding.nameTV.error = "Field cannot be empty"
            }
            //getImage
            categoriesAdapter.categoryList.add(CategoriesHelper(name, imageView = R.drawable.cats))
                 categoriesAdapter.notifyItemInserted(categoriesAdapter.categoryList.size - 1)

            Toast.makeText(requireContext(), "Added category", Toast.LENGTH_SHORT).show()
        }


        addCategoryFABinding.cancelBtn.setOnClickListener {
            Toast.makeText(requireContext(), "canceled", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        //85% of the window(width)
        val width = (resources.displayMetrics.widthPixels * 0.99).toInt()
        //40% of the window(height)
        val height = (resources.displayMetrics.widthPixels * 0.40).toInt()

        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onClick(position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }
}