package com.haris.bookhunt.trending_books

import android.util.Log
import com.haris.bookhunt.ApiInterface
import com.haris.bookhunt.models.TrendingBooksListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingBooksModel : TrendingBooksContract.Model {
    private val TAG = "TrendingBooksModel"

    override fun getTrendingBooksList(onFinishedListener: TrendingBooksContract.Model.OnFinishedListener) {
        val call = ApiInterface.create().getBooksTrendingAllTime()

        call.enqueue(object: Callback<TrendingBooksListResponse> {
            override fun onResponse(
                call: Call<TrendingBooksListResponse>,
                response: Response<TrendingBooksListResponse>
            ) {
                val body = response.body()

                if(body != null) {
                    Log.d(TAG, "Number of books trending: ${body.books.size}")
                    onFinishedListener.onFinished(body)
                } else {
                    Log.d(TAG, "Response was null")
                }
            }

            override fun onFailure(call: Call<TrendingBooksListResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })

    }
}