package com.haris.bookhunt.search

import com.haris.bookhunt.models.BookPreview
import com.haris.bookhunt.models.SearchResponse

interface SearchContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(response: SearchResponse)
            fun onFailure(t: Throwable)
        }

        fun getTrendingBooksList(onFinishedListener: OnFinishedListener, query: String)
    }

    interface View {
        fun hideProgress()
        fun showProgress()

        fun setDataToRecyclerView(data: List<BookPreview>)
        fun clearRecyclerView()

        fun onResponseFailure(t: Throwable)
    }

    interface Presenter {
        fun onDestroy()

        fun getData(query: String)
    }
}