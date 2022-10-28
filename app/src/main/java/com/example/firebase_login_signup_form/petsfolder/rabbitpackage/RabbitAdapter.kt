package com.example.firebase_login_signup_form.petsfolder.rabbitpackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class RabbitAdapter : ListAdapter<PetsHelper, RabbitAdapter.RabbitViewHolder>(Diffutil()) {

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
    ): RabbitViewHolder {
        return RabbitViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RabbitViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageNameId.text = currentList[position].petsName
        }
    }

    class RabbitViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}