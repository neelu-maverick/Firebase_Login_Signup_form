package com.example.firebase_login_signup_form.petsfolder.catspackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class CatsAdapter : ListAdapter<PetsHelper, CatsAdapter.CatsViewHolder>(Diffutil()) {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsAdapter.CatsViewHolder {
        return CatsAdapter.CatsViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: CatsAdapter.CatsViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageNameId.text = currentList[position].petsName
        }
    }

    class CatsViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}