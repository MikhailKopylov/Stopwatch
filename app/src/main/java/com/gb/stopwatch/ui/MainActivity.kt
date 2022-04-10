package com.gb.stopwatch.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.R
import com.gb.stopwatch.presentation.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModelFirst: MainActivityViewModel = MainActivityViewModel()
    private val viewModelSecond: MainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStopwatchFirst()
        initStopwatchSecond()
        initStopwatchAll()
    }

    private fun initStopwatchFirst() {
        val textView = findViewById<TextView>(R.id.text_time)
        viewModelFirst.liveData.observe(this) {
            textView.text = it
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            viewModelFirst.start()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            viewModelFirst.pause()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            viewModelFirst.stop()
        }
    }


    private fun initStopwatchSecond() {
        val textView = findViewById<TextView>(R.id.text_time_2)
        viewModelSecond.liveData.observe(this) {
            textView.text = it
        }

        findViewById<Button>(R.id.button_start_2).setOnClickListener {
            viewModelSecond.start()
        }
        findViewById<Button>(R.id.button_pause_2).setOnClickListener {
            viewModelSecond.pause()
        }
        findViewById<Button>(R.id.button_stop_2).setOnClickListener {
            viewModelSecond.stop()
        }
    }

    private fun initStopwatchAll() {
        findViewById<Button>(R.id.button_start_all).setOnClickListener {
            viewModelSecond.start()
            viewModelFirst.start()
        }
        findViewById<Button>(R.id.button_pause_all).setOnClickListener {
            viewModelSecond.pause()
            viewModelFirst.pause()
        }
        findViewById<Button>(R.id.button_stop_all).setOnClickListener {
            viewModelSecond.stop()
            viewModelFirst.stop()
        }
    }
}


