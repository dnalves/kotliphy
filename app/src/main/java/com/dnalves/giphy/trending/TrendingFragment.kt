package com.dnalves.giphy.trending

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dnalves.giphy.R
import com.dnalves.giphy.network.DataEnvelope
import com.dnalves.giphy.network.DataGif
import com.dnalves.giphy.network.GiphyNetwork
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.trending_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TrendingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.trending_fragment, container, false) as SwipeRefreshLayout?


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadTrending()

    }

    private fun loadTrending() {
        GiphyNetwork.giphyService.trending().enqueue(object : Callback<DataEnvelope<List<DataGif>>> {
            override fun onResponse(call: Call<DataEnvelope<List<DataGif>>>?, response: Response<DataEnvelope<List<DataGif>>>?) {
                trending_grid.adapter = GifAdapter(response!!.body()!!.data, context)
            }

            override fun onFailure(call: Call<DataEnvelope<List<DataGif>>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun setImageAndStopRefreshing(imageUrl: String) {
        Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(imageUrl))
                .setAutoPlayAnimations(true)
                .build()
        trending_content.isRefreshing = false
    }
}

class GifAdapter(private val gifList: List<DataGif>, private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: SimpleDraweeView = if (convertView == null) {
            SimpleDraweeView(context)
        } else {
            convertView as SimpleDraweeView
        }

        view.layoutParams = ViewGroup.LayoutParams(100, )

        setImageOnView(view, gifList[position].images.fixedHeightSmall.url)
        return view
    }

    override fun getItem(position: Int): Any = gifList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = gifList.size

    private fun setImageOnView(view: SimpleDraweeView, imageUrl: String) {
        view.controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(imageUrl))
                .setAutoPlayAnimations(true)
                .build()
    }

}


