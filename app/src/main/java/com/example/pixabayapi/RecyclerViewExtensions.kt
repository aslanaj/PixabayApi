package com.example.pixabayapi

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollToEnd(linearLayoutManager: LinearLayoutManager, onScrollNearEnd: (Unit) -> Unit) =
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (linearLayoutManager.childCount + linearLayoutManager.findFirstVisibleItemPosition()
                >= linearLayoutManager.itemCount - 3) {
                onScrollNearEnd(Unit)
            }
        }
    })
