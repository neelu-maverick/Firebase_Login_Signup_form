package com.example.firebase_login_signup_form.petsfolder.turtlepackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.SingleRowDataBinding
import com.example.firebase_login_signup_form.dataclasses.PetsHelper

class TurtleAdapter : ListAdapter<PetsHelper, TurtleAdapter.TurtleViewHolder>(Diffutil()) {

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
    ): TurtleViewHolder {
        return TurtleViewHolder(SingleRowDataBinding.inflate(LayoutInflater.from(
            parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: TurtleViewHolder, position: Int) {
        SingleRowDataBinding.bind(holder.itemView).apply {
            imageViewId.setImageResource(currentList[position].petsImage)
            imageNameId.text = currentList[position].petsName

            holder.itemView.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(R.id.action_turtleFragment_to_pictureFragment,
                        Bundle().apply {
                            putString("ImageName", imageNameId.text as String)
                            putInt("ImageView", currentList[position].petsImage)
                        })
            }
        }
    }

    class TurtleViewHolder(binding: SingleRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}