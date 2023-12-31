package com.example.firebase_login_signup_form.videofolder

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.firebase_login_signup_form.R

class PlayerViewHolder(private val parent: View) : RecyclerView.ViewHolder(
    parent
) {
    /**
     * below view have public modifier because
     * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
     */
    var mediaContainer: FrameLayout = parent.findViewById(R.id.mediaContainer)
    var mediaCoverImage: ImageView = parent.findViewById(R.id.ivMediaCoverImage)
    var volumeControl: ImageView = parent.findViewById(R.id.ivVolumeControl)
    var progressBar: ProgressBar = parent.findViewById(R.id.progressBar)
    var requestManager: RequestManager? = null
    private val title: TextView = parent.findViewById(R.id.tvTitle)
    private val userHandle: TextView = parent.findViewById(R.id.tvUserHandle)

    fun onBind(mediaObject: MediaObjectHelper, requestManager: RequestManager?) {
        this.requestManager = requestManager
        parent.tag = this
        title.text = mediaObject.title
        userHandle.text = mediaObject.userHandle
        this.requestManager
            ?.load(mediaObject.coverUrl)
            ?.into(mediaCoverImage)
    }

}