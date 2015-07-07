package com.htouch.constant

import akka.util.ByteString
import com.typesafe.config._

/**
 * Created by superleo on 30/6/15.
 */
object Constants {

  // Load our own config values from the default location, application.conf
  val conf = ConfigFactory.load()
  var driver = conf.getString("db.driver")
  var url = conf.getString("db.url")
  var username = conf.getString("db.username")
  var password = conf.getString("db.password")

  val APP_NAME = conf.getString("APP_NAME")
  val PORT = conf.getString("PORT").toInt

  val ACT_OK: ByteString = ByteString("OK")
  val ACT_ERROR: ByteString = ByteString("ERROR")
  val OK: String = new String(Array[Byte](0x4f.toByte, 0x4b.toByte))
  val NO: String = new String(Array[Byte](0x4e.toByte, 0x4f.toByte))
  val START: String = new String(Array[Byte](0x55.toByte, 0x33.toByte))
  val END: String = new String(Array[Byte](0x0d.toByte, 0x0a.toByte))

}
