package com.example.daggerpractice2.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerpractice2.R
import com.example.daggerpractice2.ui.main.Resource
import com.example.daggerpractice2.util.VerticalSpaceItemDecoration
import com.example.daggerpractice2.viewmodels.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostFragments  : DaggerFragment(){
    //const
    private val TAG = "PostFragments"
    //vars
    lateinit var postViewModel: PostViewModel

    @Inject
    lateinit var verticalSpaceItemDecoration: VerticalSpaceItemDecoration
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var postRecyclerAdapter: PostRecyclerAdapter
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postViewModel = ViewModelProviders.of(this, viewModelProvFactory).get(PostViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        postViewModel.observePost().removeObservers(viewLifecycleOwner)
        postViewModel.observePost().observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    Resource.Status.LOADING -> {Log.i(TAG, "onChanged: LOADING...")}
                    Resource.Status.SUCCESS -> {Log.i(TAG, "onChanged: got posts...")
                    postRecyclerAdapter.setPosts(it.data!!)}
                    Resource.Status.ERROR -> {Log.i(TAG, "onChanged: Error... ${it.message}")}
                }
            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.addItemDecoration(verticalSpaceItemDecoration)
        recycler_view.adapter = postRecyclerAdapter
    }

}