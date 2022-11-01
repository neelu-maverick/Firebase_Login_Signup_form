package com.example.firebase_login_signup_form.petsfolder.horsepackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class HorsesAdapter : ListAdapter<PetsHelper, HorsesAdapter.HorsesViewHolder>(Diffutil()) {

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


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorsesAdapter.HorsesViewHolder {
        return HorsesAdapter.HorsesViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: HorsesAdapter.HorsesViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageViewId.setImageResource(currentList[position].petsImage)
            imageNameId.text = currentList[position].petsName
        }
    }

    class HorsesViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}