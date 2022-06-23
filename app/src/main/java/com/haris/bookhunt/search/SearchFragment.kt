package com.haris.bookhunt.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haris.bookhunt.R
import com.haris.bookhunt.adapters.SearchAdapter
import com.haris.bookhunt.models.BookPreview

class SearchFragment : Fragment(), SearchContract.View {
    private val TAG = "SearchFragment"
    private lateinit var presenter: SearchPresenter
    private var searchResultsList = mutableListOf<BookPreview>()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.f_search_progress)

        var editText = view.findViewById<EditText>(R.id.f_search_et)
        editText.text.clear()

        recyclerView = view.findViewById(R.id.f_search_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter = SearchAdapter(requireContext(), searchResultsList)
        recyclerView.adapter = recyclerViewAdapter

        presenter = SearchPresenter(this)

        view.findViewById<Button>(R.id.f_search_butt).setOnClickListener {
            presenter.getData(editText.text.toString().trim().replace(' ', '+').lowercase())
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun setDataToRecyclerView(data: List<BookPreview>) {
        searchResultsList.clear()
        searchResultsList.addAll(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun clearRecyclerView() {
        searchResultsList.clear()
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onResponseFailure(t: Throwable) {
        Log.e(TAG, t.toString())
        Toast.makeText(requireContext(), "Failed to get response", Toast.LENGTH_LONG).show()
    }
}