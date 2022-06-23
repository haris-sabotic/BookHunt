package com.haris.bookhunt.trending_books

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haris.bookhunt.R
import com.haris.bookhunt.adapters.TrendingBooksAdapter
import com.haris.bookhunt.models.BookPreview

class TrendingBooksFragment : Fragment(), TrendingBooksContract.View {
    private val TAG = "TrendingBooksFragment"
    private lateinit var presenter: TrendingBooksPresenter
    private var trendingBooksList = mutableListOf<BookPreview>()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TrendingBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.f_trending_books_progress)

        recyclerView = view.findViewById(R.id.f_trending_books_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter = TrendingBooksAdapter(requireContext(), trendingBooksList)
        recyclerView.adapter = recyclerViewAdapter


        presenter = TrendingBooksPresenter(this)
        presenter.getData()
    }


    override fun setDataToRecyclerView(data: List<BookPreview>) {
        trendingBooksList.clear()
        trendingBooksList.addAll(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun clearRecyclerView() {
        trendingBooksList.clear()
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onResponseFailure(t: Throwable) {
        Log.e(TAG, t.toString())
        Toast.makeText(requireContext(), "Failed to get response", Toast.LENGTH_LONG).show()
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }
}