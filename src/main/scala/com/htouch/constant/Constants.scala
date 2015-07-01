package com.htouch.constant

/**
 * Created by superleo on 30/6/15.
 */
object Constants {

  var url = "jdbc:mysql://localhost:3306/bracelet?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8"
  var username = "root"
  var password = "root"
  var driver = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource"

  val APP_NAME = "HTOUCH_SERVER"
  val PORT = 8080

  val OK: String = new String(Array[Byte](0x4f.toByte, 0x4b.toByte))
  val NO: String = new String(Array[Byte](0x4e.toByte, 0x4f.toByte))
  val START: String = new String(Array[Byte](0x55.toByte, 0x33.toByte))
  val END: String = new String(Array[Byte](0x0d.toByte, 0x0a.toByte))
}
