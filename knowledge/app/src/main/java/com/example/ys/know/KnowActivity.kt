package com.example.ys.know

import android.net.Uri
import android.os.Bundle

import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.ys.know.dummy.DummyContent
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class KnowActivity() : AppCompatActivity() , KnowFragment.OnListFragmentInteractionListener, AddFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    private val knowFragment: KnowFragment = KnowFragment()
    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onListFragmentInteraction(item: KnowData?) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_add -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, AddFragment.newInstance("",""))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, SearchFragment.newInstance("",""))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, KnowFragment.newInstance(1))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_know)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragment, KnowFragment.newInstance(1))
                    .commit()
        }
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val knowApi = KnowApi()
        val knowList = ArrayList<KnowData>()
        knowApi.getKnowList { tmpKnowList ->
            tmpKnowList?.forEach {data ->
                data?.let{
                    knowList.add(data)
                }
            }
            knowFragment.OnListFragmentItemChanged(knowList)
        }
    }
}
