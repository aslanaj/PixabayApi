package com.example.pixabayapi.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabayapi.ImageModel
import com.example.pixabayapi.PixabayModel
import com.example.pixabayapi.databinding.ActivityMainBinding
import com.example.pixabayapi.onScrollToEnd
import com.example.pixabayapi.remote.RetrofitServices
import com.example.pixabayapi.ui.adapter.PixabayAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var page = 1
    var adapter = PixabayAdapter(arrayListOf())
    var list = arrayListOf<ImageModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
        initRecyclerView()
    }
    private fun initRecyclerView() {
        binding.apply {
            rvImages.layoutManager = LinearLayoutManager(this@MainActivity)
            rvImages.setHasFixedSize(true)
            rvImages.adapter = adapter
            rvImages.onScrollToEnd(linearLayoutManager = rvImages.layoutManager as LinearLayoutManager) {
                ++page
                requestImage(page)
            }
        }
    }



    private fun initClickListener() {
        binding.apply {
            btnLoadMore.setOnClickListener {
                ++page
                requestImage(page)
            }
            btnSearch.setOnClickListener {
                page = 1
                adapter.list.clear()
                requestImage(page)
            }
        }
    }

    private fun requestImage(page: Int) {
        RetrofitServices().api.getImages(binding.etSearch.text.toString(), page)
            .enqueue(object : Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    response.body()?.let {
                        list = it.hits
                        adapter.addImages(list)
                        binding.rvImages.adapter = adapter
                    }

                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }
            })
    }

}