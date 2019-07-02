package sky.movies.features.detail.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_details.*
import sky.movies.R
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.loadImg

class DetailDialog(context: Context, private val moviesData: MoviesData) :
    Dialog(context, R.style.full_screen_dialog_information) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = View.inflate(context, R.layout.activity_details, null)
        setContentView(contentView)
        (contentView.parent as? View)?.fitsSystemWindows = true

        setUp()
        setViewValues()
    }

    private fun setUp() {
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        setCancelable(true)
        setCanceledOnTouchOutside(true)
    }

    private fun setViewValues() {
        ivCover.loadImg(moviesData.backdrops.first())
        tvTitle.text = moviesData.title
        tvDuration.text = moviesData.duration
        tvYear.text = moviesData.release_year
        tvOverview.text = moviesData.overview
    }
}
