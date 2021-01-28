package com.mic.movie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.mic.movie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieFragment())
        }
        var messageArgs = arguments?.let {
            MovieDetailFragmentArgs.fromBundle(it)
        }
       if(messageArgs!=null){

           Picasso.get()
               .load("https://image.tmdb.org/t/p/w200${messageArgs.coverimg}")
               .placeholder(R.drawable.ic_launcher_background)
               .into(root.img_coverimg)
           root.txt_movieName.text=messageArgs.movieName
           root.txt_secondMovieName.text=messageArgs.movieName
           root.movie_date.text=messageArgs.movieDate+"|"
           root.txt_imdbrate.text=messageArgs.movieRating
           root.txt_overview.text=messageArgs.movieOverReview

       }
        root.img_backarrow.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieFragment())
        }
        return root
    }

}