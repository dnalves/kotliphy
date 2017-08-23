package com.dnalves.giphy.trending

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnalves.giphy.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView


class TrendingFragment : Fragment() {

    private var root: View? = null

    private var randomImageView: SimpleDraweeView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater?.inflate(R.layout.trending_fragment, container, false)

        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomImageView = root?.findViewById(R.id.trending_random_image_view) as SimpleDraweeView
        randomImageView?.controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("https://media0.giphy.com/media/l0NwuvFERvrjszjd6/giphy.gif"))
                .setAutoPlayAnimations(true)
                .build()
    }
}
