package com.example.ys.know

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ys.know.dummy.DummyContent
import java.text.SimpleDateFormat
import java.util.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [KnowFragment.OnListFragmentInteractionListener] interface.
 */
open class KnowFragment : Fragment() {
    private var dataList: MutableList<KnowData> = ArrayList()
    //private val dataMap: MutableMap<String,KnowData> = HashMap()
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    private val dataCount = 25

    init {
        // Add some sample items.
        for (i in 1..dataCount) {
            addItem(createKnowData(i))
        }
    }
    fun OnListFragmentItemChanged(knowList: ArrayList<KnowData>){
       replaceItem(knowList)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_know_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyKnowRecyclerViewAdapter(dataList, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: KnowData?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                KnowFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }



    private fun addItem(item: KnowData) {
        dataList.add(item)
        //dataMap.put(item.id, item)
    }
    fun replaceItem( knowList: ArrayList<KnowData>) {
        knowList?.let{it ->
            dataList = it
        }
    }
    private fun createKnowData(position: Int): KnowData {
        val df = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()
        val category = "default"
        return KnowData(position.toString(), "Item " + position, makeDetails(position), category, df.format(date) ,df.format(date))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

}
