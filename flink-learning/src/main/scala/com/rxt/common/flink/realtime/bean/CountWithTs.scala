package com.rxt.common.flink.realtime.bean

case class CountWithTs(key: String, count: Long, lastModified: Long)
