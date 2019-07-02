package sky.movies.utils

import androidx.lifecycle.LiveData

class ResourceResponse<T> (
    val state: LiveData<ViewState>,
    val data: LiveData<T>
)

enum class ViewState {
    LOADING, SUCCESS, ERROR
}