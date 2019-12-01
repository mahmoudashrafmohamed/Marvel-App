package com.dev.mahmoud_ashraf.marvelapp.utils

import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

class EndlessScroll(
    private val recyclerView: RecyclerView,
    val visibleThreshold: Int = 10,
    private val loadMore: () -> Unit
) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 1
    private var loading = true

    class AccessibilityDelegate(recyclerView: RecyclerView, var changeRow: () -> Unit) :
        RecyclerViewAccessibilityDelegate(recyclerView) {

        override fun onRequestSendAccessibilityEvent(
            host: ViewGroup?,
            child: View?,
            event: AccessibilityEvent?
        ): Boolean {
            event?.let {
                if (it.eventType == TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                    changeRow()
                }
            }

            return super.onRequestSendAccessibilityEvent(host, child, event)
        }
    }

    init {
        recyclerView.setAccessibilityDelegateCompat(
            AccessibilityDelegate(
                recyclerView,
                ::changeRowByAccessibility
            )
        )
    }

    private fun changeRowByAccessibility() {
        if (this.recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
            return
        }

        loading()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (this.recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            return
        }

        loading()
    }

    private fun loading() {
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loadMore()
            loading = true
        }
    }
}

fun RecyclerView.endless(visibleThreshold: Int = 10, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}