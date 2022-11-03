package com.example.firebase_login_signup_form.petsfolder.birdspackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.OnClickListener
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class BirdsAdapter(var clickListener: OnClickListener) :
    ListAdapter<PetsHelper, BirdsAdapter.BirdsViewHolder>(Diffutil()) {

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
        viewType: Int,
    ): BirdsAdapter.BirdsViewHolder {
        return BirdsAdapter.BirdsViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: BirdsAdapter.BirdsViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageViewId.setImageResource(currentList[position].petsImage)
            imageNameId.text = currentList[position].petsName
            holder.init(clickListener)
        }
    }

    class BirdsViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun init(action: OnClickListener) {

            itemView.setOnClickListener {
                action.onClick(adapterPosition)
            }
        }
    }
}