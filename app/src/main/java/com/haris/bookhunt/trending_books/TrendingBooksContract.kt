package com.haris.bookhunt.trending_books

import com.haris.bookhunt.models.BookPreview
import com.haris.bookhunt.models.TrendingBooksListResponse

interface TrendingBooksContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(response: TrendingBooksListResponse)
            fun onFailure(t: Throwable)
        }

        fun getTrendingBooksList(onFinishedListener: OnFinishedListener)
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

        fun getData()
    }
}