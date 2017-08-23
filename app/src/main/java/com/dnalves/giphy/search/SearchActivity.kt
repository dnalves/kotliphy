package com.dnalves.giphy.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dnalves.giphy.R
import com.dnalves.giphy.common.bind

class SearchActivity : AppCompatActivity() {

    private val inputView: EditText by bind(R.id.search_input)

    private val button: Button by bind(R.id.search_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        button.setOnClickListener {
            Toast.makeText(this, inputView.text, Toast.LENGTH_SHORT).show()
        }

    }

}
