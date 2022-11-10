package com.example.firebase_login_signup_form.floatingaction

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.firebase_login_signup_form.databinding.FragmentAddCategoryFaBinding
import java.util.*

class AddCategoryFAFragment : DialogFragment() {

    lateinit var addCategoryFABinding: FragmentAddCategoryFaBinding
    //var getResult: ActivityResultLauncher<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        addCategoryFABinding =
            FragmentAddCategoryFaBinding.inflate(inflater, container, false)
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

        addCategoryFABinding.addCategBtn.setOnClickListener {
            var name: String = addCategoryFABinding.nameid.text.toString()
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
}