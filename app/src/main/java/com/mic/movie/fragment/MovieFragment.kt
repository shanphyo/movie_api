package com.mic.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.mic.movie.R
import com.mic.movie.adapter.MovieClick
import com.mic.movie.adapter.MoviesAdapter
import com.mic.movie.model.Result
import com.mic.movie.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*


class MovieFragment : Fragment(),MovieClick {
    private lateinit var moviesListAdapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_movie, container, false)
        root.progress.setVisibility(View.VISIBLE);


        val slideModels: MutableList<SlideModel> = ArrayList()
        slideModels.add(
            SlideModel(
                "https://i.pinimg.com/originals/da/39/62/da396281efe27c6e2af11de42b650044.png",
                "PIXIE"
            )
        )
        slideModels.add(
            SlideModel(
                "https://film-book.com/wp-content/uploads/2016/01/morgan-freeman-gerard-butler-aaron-eckhart-london-has-fallen-01-600x350.jpg",
                "LONDON HAS FALLEN"
            )
        )
        slideModels.add(
            SlideModel(
                "https://img.cinemablend.com/filter:scale/quill/6/6/2/0/1/7/6620174df16feed666145b18f4596a0b8a421467.jpg?fw=1200",
                "SPIDERMEN"
            )
        )
        slideModels.add(
            SlideModel(
                "https://subtitlesmag.com/wp-content/uploads/2019/08/Angel-Has-Fallen-Subtitles.jpg",
                "Angel Has Fallen"
            )
        )
        root.slider.setImageList(slideModels)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_movieList.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        moviesListAdapter = MoviesAdapter(this)
        recycler_movieList.adapter = moviesListAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        moviesViewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)
        moviesViewModel.loadMovies()
        moviesViewModel.topRatedMovies.observe(
            viewLifecycleOwner, Observer {
                progress.setVisibility(View.GONE);
                recycler_movieList.visibility = View.VISIBLE
                moviesListAdapter.updateResultlist(it)
            }
        )
    }

    override fun onFunClick(result: Result) {
     //   findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(result.id,))
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment2(
                result.id,
                result.poster_path,
                result.original_title,
                result.release_date,
                result.vote_average.toString(),
                result.overview
            )
        )

    }


}