package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.PlaceResponse
import kotlinx.android.synthetic.main.fragment_place.*


/**
 *  project : RecycleApp
 *  created by Terry on 2020/7/26 16:28.
 *  description :
 */

class PlaceFragment : Fragment() {
    val viewModel by lazy  { ViewModelProviders.of(this)
        .get(PlaceViewModel::class.java)}

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycleView.layoutManager = layoutManager
        adapter = PlaceAdapter(this,viewModel.placeList)
        recycleView.adapter = adapter
        searchPlaceEdit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val content = p0.toString()
                if (content.isNotEmpty()){
                    viewModel.searchPlaces(content)
                }else{
                    recycleView.visibility = View.GONE
                    bgImageView.visibility = View.VISIBLE
                    viewModel.placeList.clear()
                    adapter.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })


        viewModel.placeLiveData.observe(this, Observer { result ->
//            val places = result!!
//
//            if (places != null){
//                recycleView.visibility = View.VISIBLE
//                bgImageView.visibility = View.GONE
//                viewModel.placeList.clear()
//                viewModel.placeList.addAll()
//                adapter.notifyDataSetChanged()
//            }else{
//                Toast.makeText(activity,"未能查询到任何地点",Toast.LENGTH_SHORT).show()
//            }
            //ToDO
        })
    }
}

