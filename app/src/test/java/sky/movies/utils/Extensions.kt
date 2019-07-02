package sky.movies.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> mockResourceResponse(retry: (() -> ResourceResponse<T>)? = null): ResourceResponse<T> {
    return ResourceResponse(
        data = MutableLiveData<T>(),
        state = MutableLiveData<ViewState>()
    )
}


val <T> LiveData<T>.asMutable: MutableLiveData<T>
    get() = this as? MutableLiveData<T> ?: throw IllegalStateException("$this isn't a valid MutableLiveData")