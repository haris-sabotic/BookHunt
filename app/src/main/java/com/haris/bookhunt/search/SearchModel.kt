package com.haris.bookhunt.search

import android.util.Log
import com.haris.bookhunt.ApiInterface
import com.haris.bookhunt.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel : SearchContract.Model {
    private val TAG = "SearchModel"

    override fun getTrendingBooksList(onFinishedListener: SearchContract.Model.OnFinishedListener, query: String) {
        val call = ApiInterface.create().search(query)

        call.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val body = response.body()

                if(body != null) {
                    Log.d(TAG, "Number of search results: ${body.books.size}")
                    onFinishedListener.onFinished(body)
                } else {
                    Log.d(TAG, "Response was null")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })

    }
}