package sky.movies.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.internal.operators.observable.ObservableFromCallable

fun <T> Observable<T>.baseResult(): Observable<T> {
    return this.compose(handle())
}

private fun <T> handle(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable.flatMap { t ->
            Observable.defer {
                ObservableFromCallable.fromCallable { t }
            }
        }
    }
}

fun <T> buildResourceResponse(): Triple<MutableLiveData<T>, MutableLiveData<ViewState>, MutableLiveData<String>> {
    return Triple(MutableLiveData(), MutableLiveData(), MutableLiveData())
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { action(it) } })
}

@SuppressLint("CheckResult")
fun <T> Observable<T>.toResourceResponse(): ResourceResponse<T> {
    val (result, state) = buildResourceResponse<T>()

    this.doOnSubscribe {
        state.postValue(ViewState.LOADING)
    }.subscribe({ data ->
        result.postValue(data)
        state.postValue(ViewState.SUCCESS)
    }, {
        state.postValue(ViewState.ERROR)
    })

    return ResourceResponse(
        data = result,
        state = state
    )
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun ImageView.loadImgCroped(url: String) {
    Picasso
        .get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)
}


fun ImageView.loadImg(url: String) {
    Picasso
        .get()
        .load(url)
        .fit()
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}