package com.haris.bookhunt.search

import com.haris.bookhunt.models.SearchResponse

class SearchPresenter(
    private var view: SearchContract.View
) : SearchContract.Presenter, SearchContract.Model.OnFinishedListener {
    private var model = SearchModel()

    override fun onFinished(response: SearchResponse) {
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

    override fun getData(query: String) {
        view.clearRecyclerView()
        view.showProgress()
        model.getTrendingBooksList(this, query)
    }
}