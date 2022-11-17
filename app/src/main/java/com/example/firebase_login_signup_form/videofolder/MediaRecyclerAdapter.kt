package com.example.firebase_login_signup_form.videofolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.firebase_login_signup_form.R


class MediaRecyclerAdapter(
    private val mediaObjects: ArrayList<MediaObjectHelper>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.video_list_item_layout, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as PlayerViewHolder).onBind(mediaObjects[i], requestManager)
    }

    override fun getItemCount(): Int {
        return mediaObjects.size
    }
}