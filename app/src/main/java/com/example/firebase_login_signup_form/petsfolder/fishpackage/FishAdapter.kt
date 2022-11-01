package com.example.firebase_login_signup_form.petsfolder.fishpackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper
import com.example.firebase_login_signup_form.petsfolder.dogspackage.DogsAdapter

class FishAdapter: ListAdapter<PetsHelper, FishAdapter.FishViewHolder>(Diffutil()) {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishAdapter.FishViewHolder {
        return FishAdapter.FishViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: FishAdapter.FishViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageViewId.setImageResource(currentList[position].petsImage)
            imageNameId.text = currentList[position].petsName
        }
    }

    class FishViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}