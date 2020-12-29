package com.codinginflow.imagesearchapp.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            val photo = args.photo
            Glide.with(this@DetailsFragment)
                .load(photo.urls.full)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar3.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar3.isVisible = false
                        txtViewCreator.isVisible = true
                        txtViewDescription.isVisible = photo.description != null
                        return false
                    }

                })
                .into(photoDetailImageView)
            txtViewDescription.text = photo.description
            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            txtViewCreator.apply {
                val creator = "Photo by ${photo.user.username} on Splash"
                text = creator
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }
        }
    }
}