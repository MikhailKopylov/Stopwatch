package com.gb.stopwatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.stopwatch.domain.entity.TimestampProvider
import com.gb.stopwatch.presentation.interactors.ElapsedTimeCalculator
import com.gb.stopwatch.presentation.interactors.StopwatchStateCalculator
import com.gb.stopwatch.presentation.interactors.TimestampMillisecondsFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = _liveData

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    init {
        getData()
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                + SupervisorJob()
        )
    )

    private fun getData() {
        CoroutineScope(
            Dispatchers.Main
                + SupervisorJob()
        ).launch {
            stopwatchListOrchestrator.ticker.collect {
                _liveData.value = it
            }
        }
    }

    fun start() {
        stopwatchListOrchestrator.start()
    }

    fun stop() {
        stopwatchListOrchestrator.stop()
    }

    fun pause() {
        stopwatchListOrchestrator.pause()
    }


}
