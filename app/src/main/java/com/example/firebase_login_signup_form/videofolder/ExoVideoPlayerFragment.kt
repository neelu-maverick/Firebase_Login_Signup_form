package com.example.firebase_login_signup_form.videofolder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.firebase_login_signup_form.databinding.FragmentExoVideoPlayerBinding

class ExoVideoPlayerFragment : Fragment() {
    lateinit var videoPlayerBinding: FragmentExoVideoPlayerBinding

    private val mediaObjectList = ArrayList<MediaObjectHelper>()
    private var mAdapter: MediaRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        videoPlayerBinding = FragmentExoVideoPlayerBinding.inflate(inflater, container, false)
        return videoPlayerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        // Prepare demo content
        prepareVideoList()

        //set data object
        videoPlayerBinding.exoPlayerRecyclerView.setMediaObjects(mediaObjectList)
        mAdapter = MediaRecyclerAdapter(mediaObjectList, initGlide())

        //Set Adapter
        videoPlayerBinding.exoPlayerRecyclerView.adapter = mAdapter
        videoPlayerBinding.exoPlayerRecyclerView.smoothScrollToPosition(1)

    }

    private fun initView() {
        videoPlayerBinding.exoPlayerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
    }


    private fun initGlide(): RequestManager {
        val options = RequestOptions()
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }

    override fun onDestroy() {
        videoPlayerBinding.exoPlayerRecyclerView.releasePlayer()
        super.onDestroy()
    }

    private fun prepareVideoList() {
        val mediaObject = MediaObjectHelper()
        mediaObject.id = 1
        mediaObject.userHandle = "User 1"
        mediaObject.title = "Item 1"
        mediaObject.coverUrl =
            "https://media.istockphoto.com/id/477721539/photo/play-video-audio-music-on-blackboard.jpg?b=1&s=170667a&w=0&k=20&c=fkAXgb9-BH9Y32axO05C9uF3O6cIOKjPxFyuc-9Hb-4="
        mediaObject.url =
            "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"

        val mediaObject2 = MediaObjectHelper()
        mediaObject2.id = 2
        mediaObject2.userHandle = "user 2"
        mediaObject2.title = "Item 2"
        mediaObject2.coverUrl =
            "https://media.istockphoto.com/id/477721539/photo/play-video-audio-music-on-blackboard.jpg?b=1&s=170667a&w=0&k=20&c=fkAXgb9-BH9Y32axO05C9uF3O6cIOKjPxFyuc-9Hb-4="
        mediaObject2.url =
            "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"

        val mediaObject3 = MediaObjectHelper()
        mediaObject3.id = 3
        mediaObject3.userHandle = "User 3"
        mediaObject3.title = "Item 3"
        mediaObject3.coverUrl =
            "https://media.istockphoto.com/id/477721539/photo/play-video-audio-music-on-blackboard.jpg?b=1&s=170667a&w=0&k=20&c=fkAXgb9-BH9Y32axO05C9uF3O6cIOKjPxFyuc-9Hb-4="
        mediaObject3.url =
            "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"

        val mediaObject4 = MediaObjectHelper()
        mediaObject4.id = 4
        mediaObject4.userHandle = "User 4"
        mediaObject4.title = "Item 4"
        mediaObject4.coverUrl =
            "https://media.istockphoto.com/id/477721539/photo/play-video-audio-music-on-blackboard.jpg?b=1&s=170667a&w=0&k=20&c=fkAXgb9-BH9Y32axO05C9uF3O6cIOKjPxFyuc-9Hb-4="
        mediaObject4.url =
            "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"

        val mediaObject5 = MediaObjectHelper()
        mediaObject5.id = 5
        mediaObject5.userHandle = "User 5"
        mediaObject5.title = "Item 5"
        mediaObject5.coverUrl =
            "https://media.istockphoto.com/id/477721539/photo/play-video-audio-music-on-blackboard.jpg?b=1&s=170667a&w=0&k=20&c=fkAXgb9-BH9Y32axO05C9uF3O6cIOKjPxFyuc-9Hb-4="
        mediaObject5.url =
            "https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"
        mediaObjectList.add(mediaObject)
        mediaObjectList.add(mediaObject2)
        mediaObjectList.add(mediaObject3)
        mediaObjectList.add(mediaObject4)
        mediaObjectList.add(mediaObject5)
    }

}