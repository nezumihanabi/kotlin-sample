package com.example.ys.know

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.ys.know.KnowFragment.OnListFragmentInteractionListener
import com.example.ys.know.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_know.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyKnowRecyclerViewAdapter(
        private val mValues: List<KnowData>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyKnowRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as KnowData
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_know, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.content
        holder.mCategoryView.text = item.category
        holder.mUpdateDateView.text = item.updateDate

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mCategoryView: TextView = mView.category
        val mUpdateDateView: TextView = mView.update_date

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
