package com.example.firebase_login_signup_form.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_login_signup_form.OnClickListener
import com.example.firebase_login_signup_form.databinding.CategoriesGridViewBinding
import com.example.firebase_login_signup_form.dataclasses.CategoriesHelper

class CategoriesAdapter(
    var clickListener: OnClickListener,
    val categoryList: ArrayList<CategoriesHelper>,
) :
    ListAdapter<CategoriesHelper, CategoriesAdapter.ViewHolder>(Diffutil()) {


    class Diffutil : DiffUtil.ItemCallback<CategoriesHelper>() {
        override fun areItemsTheSame(
            oldItem: CategoriesHelper,
            newItem: CategoriesHelper,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoriesHelper,
            newItem: CategoriesHelper,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoriesGridViewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        CategoriesGridViewBinding.bind(holder.itemView).apply {

            imageViewId.setImageResource(currentList[position].imageView)
            imageNameId.text = currentList[position].imageName
            holder.init(clickListener)
        }
    }

    class ViewHolder(binding: CategoriesGridViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun init(action: OnClickListener) {

            itemView.setOnClickListener {
                action.onClick(adapterPosition)
            }
        }
    }
}
