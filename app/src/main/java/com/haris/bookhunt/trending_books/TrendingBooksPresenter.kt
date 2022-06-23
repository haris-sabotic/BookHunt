package com.haris.bookhunt.trending_books

import com.haris.bookhunt.models.TrendingBooksListResponse

class TrendingBooksPresenter(
    private var view: TrendingBooksContract.View
) : TrendingBooksContract.Presenter, TrendingBooksContract.Model.OnFinishedListener {
    private var model = TrendingBooksModel()

    override fun onFinished(response: TrendingBooksListResponse) {
        view.setDataToRecyclerView(response.books)
        view.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        view.onResponseFailure(t)
        view.hideProgress()
    }

    override fun onDestroy() {
        view.clearRecyclerView()
    }

    override fun getData() {
        view.clearRecyclerView()
        view.showProgress()
        model.getTrendingBooksList(this)
    }
}