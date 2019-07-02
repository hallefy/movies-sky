package sky.movies.features.list.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item_list.view.*
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.loadImgCroped

class MoviesViewHolder(
    view: View,
    private val onClick: (MoviesData) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(moviesData: MoviesData) {
        itemView.apply {
            ivImage.loadImgCroped(moviesData.backdrops.first())
            tvName.text = moviesData.title
            tvYear.text = moviesData.release_year
            tvDuration.text = moviesData.duration

            cardMovie.setOnClickListener {
                onClick(moviesData)
            }
        }
    }
}