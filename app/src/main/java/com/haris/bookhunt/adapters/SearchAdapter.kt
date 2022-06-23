package com.haris.bookhunt.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haris.bookhunt.R
import com.haris.bookhunt.models.BookPreview

class SearchAdapter(
    val ctx: Context,
    var data: List<BookPreview>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title = itemView.findViewById<TextView>(R.id.i_search_title)
        private var image = itemView.findViewById<ImageView>(R.id.i_search_img)

        fun bind(pos: Int) {
            title.text = data[pos].title
            Glide.with(ctx)
                .load("https://covers.openlibrary.org/b/id/${data[pos].coverId}-M.jpg")
                .into(image)

            itemView.setOnClickListener {
                val url = "http://www.openlibrary.org${data[pos].key}"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ctx.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_search, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}