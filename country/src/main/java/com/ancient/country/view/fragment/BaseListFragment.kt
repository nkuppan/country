package com.ancient.country.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ancient.country.R
import com.ancient.country.extention.autoCleared
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by ancientinc on 2019-06-01.
 **/

abstract class BaseListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mRecyclerView by autoCleared<RecyclerView>()
    private var mLoaderWidget by autoCleared<TextView>()
    private var mSwipeRefreshLayout by autoCleared<SwipeRefreshLayout>()
    private var mActionButton by autoCleared<FloatingActionButton>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView(view)

        createRequest(true)
    }

    abstract fun createRequest(isStart: Boolean = false)

    private fun initializeView(aView: View) {

        mRecyclerView = aView.findViewById(R.id.recyclerview)
        mLoaderWidget = aView.findViewById(R.id.loader_widget)
        mSwipeRefreshLayout = aView.findViewById(R.id.refresh_layout)
        mActionButton = aView.findViewById(R.id.action_button)

        mRecyclerView.layoutManager = GridLayoutManager(context, 1)
        mRecyclerView.itemAnimator = DefaultItemAnimator()

        mSwipeRefreshLayout.setOnRefreshListener(this)
    }

    fun setHasFixedSize(aFixedSize: Boolean) {
        mRecyclerView.setHasFixedSize(aFixedSize)
    }

    protected fun setActionIconDrawable(@DrawableRes aDrawable: Int) {
        mActionButton.setImageResource(aDrawable)
    }

    protected fun setActionClickListener(aClickListener: View.OnClickListener) {
        mActionButton.visibility = View.VISIBLE
        mActionButton.setOnClickListener(aClickListener)
    }

    protected fun hideActionButton() {
        mActionButton.visibility = View.GONE
    }

    protected fun startLoadingRequest() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    protected fun setNoResult() {
        mSwipeRefreshLayout.isRefreshing = false
        mLoaderWidget.visibility = View.VISIBLE
    }

    protected fun setResult() {
        mSwipeRefreshLayout.isRefreshing = false
        mLoaderWidget.visibility = View.GONE
    }

    protected fun setAdapter(aAdapter: RecyclerView.Adapter<*>) {
        mRecyclerView.adapter = aAdapter
    }

    override fun onRefresh() {
        createRequest()
    }
}