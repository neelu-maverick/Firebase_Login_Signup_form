package com.example.firebase_login_signup_form.petsfolder.dogspackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class DogsAdapter : ListAdapter<PetsHelper, DogsAdapter.DogsViewHolder>(Diffutil()) {

    class Diffutil : DiffUtil.ItemCallback<PetsHelper>() {
        override fun areItemsTheSame(
            oldItem: PetsHelper,
            newItem: PetsHelper,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PetsHelper,
            newItem: PetsHelper,
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.DogsViewHolder {
        return DogsAdapter.DogsViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: DogsAdapter.DogsViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageViewId.setImageResource(currentList[position].petsImage)
            imageNameId.text = currentList[position].petsName
        }
    }

    class DogsViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}