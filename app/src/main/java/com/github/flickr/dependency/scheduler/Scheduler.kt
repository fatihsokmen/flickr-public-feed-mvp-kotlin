package com.github.flickr.dependency.scheduler

interface Scheduler {

    fun background(): rx.Scheduler

    fun main(): rx.Scheduler

    fun immediate(): rx.Scheduler
}