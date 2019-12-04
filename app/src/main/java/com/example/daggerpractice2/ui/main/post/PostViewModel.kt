package com.example.daggerpractice2.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice2.SessionManager
import com.example.daggerpractice2.models.Post
import com.example.daggerpractice2.network.main.MainApi
import com.example.daggerpractice2.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostViewModel @Inject constructor(mainApi: MainApi, sessionManager: SessionManager) : ViewModel(){
    //const
    private val TAG = "PostViewModel"
    //vars
    private val mainApi: MainApi = mainApi
    private val sessionManager: SessionManager = sessionManager
    private lateinit var post: MediatorLiveData<Resource<List<Post>>>

    init {
        Log.i(TAG,"PostViewModel: Viewmodel is working")
    }

    fun observePost(): LiveData<Resource<List<Post>>> {

        if (!::post.isInitialized) {
            post = MediatorLiveData()
            post.value = Resource.loading(null as List<Post>?)
            Log.d(TAG, "observePosts: user id: " + sessionManager.getAuthUser().value!!.data!!.id!!)
            val source = LiveDataReactiveStreams.fromPublisher(

                mainApi.getPostFromUser(sessionManager.getAuthUser().value!!.data!!.id!!)

                    // instead of calling onError, do this
                    .onErrorReturn { throwable ->
                        Log.e(TAG, "apply: ", throwable)
                        val post = Post()
                        post.id = -1
                        val posts: ArrayList<Post> = ArrayList()
                        posts.add(post)
                        posts
                    }

                    .map(Function<List<Post>, Resource<List<Post>>> { posts ->
                        if (posts.isNotEmpty()) {
                            if (posts[0].id == -1) {
                                return@Function Resource.error("Something went wrong", null)
                            }
                        }
                        Resource.success(posts)
                    })
                    .subscribeOn(Schedulers.io())
            )

            post.addSource(source) {
                post.value = it
                post.removeSource(source)
            }
        }
        return post
    }


}