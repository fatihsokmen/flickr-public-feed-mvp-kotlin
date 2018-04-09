package com.github.flickr.dependency.scheduler

import javax.inject.Inject

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SchedulerImpl @Inject constructor() : Scheduler {

    override fun background(): rx.Scheduler {
        return Schedulers.io()
    }

    override fun main(): rx.Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun immediate(): rx.Scheduler {
        return Schedulers.immediate()
    }
}
