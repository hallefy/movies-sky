package sky.movies.features.list.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sky.movies.R
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.inflate

class MoviesAdapter(
    private val moviesList: List<MoviesData>,
    private val onClick: (MoviesData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent.inflate(R.layout.card_item_list), onClick)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MoviesViewHolder).bind(moviesList[position])
    }
}