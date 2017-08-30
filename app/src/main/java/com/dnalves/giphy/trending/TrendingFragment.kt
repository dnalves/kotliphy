package com.dnalves.giphy.trending

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnalves.giphy.R
import com.dnalves.giphy.network.DataEnvelope
import com.dnalves.giphy.network.DataRandom
import com.dnalves.giphy.network.GiphyNetwork
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TrendingFragment : Fragment() {

    private var root: SwipeRefreshLayout? = null

    private var randomImageView: SimpleDraweeView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater?.inflate(R.layout.trending_fragment, container, false) as SwipeRefreshLayout?

        root?.setOnRefreshListener { reloadRandomImage() }

        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomImageView = root?.findViewById(R.id.trending_random_image_view) as SimpleDraweeView

        reloadRandomImage()

    }

    private fun reloadRandomImage() {
        GiphyNetwork.giphyService.random().enqueue(object : Callback<DataEnvelope<DataRandom>> {
            override fun onFailure(call: Call<DataEnvelope<DataRandom>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DataEnvelope<DataRandom>>?, response: Response<DataEnvelope<DataRandom>>?) {
                setImageAndStopRefreshing(response!!.body()!!.data.imageUrl)
            }

        })

    }

    private fun setImageAndStopRefreshing(imageUrl: String) {
        randomImageView?.controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(imageUrl))
                .setAutoPlayAnimations(true)
                .build()
        root?.isRefreshing = false
    }
}
