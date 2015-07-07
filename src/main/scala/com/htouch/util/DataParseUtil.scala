/**
 * Created by superleo on 30/6/15.
 */
package com.htouch.util

import akka.util.ByteString
import com.htouch.constant.Constants
import com.htouch.domain.Bracelet
import org.slf4j.LoggerFactory

object DataParseUtil {
  val LOGGER = LoggerFactory.getLogger(DataParseUtil.getClass)

  def bytes2hex(bytes: ByteString): String = {
    bytes.map("%02x".format(_)).mkString
  }

  def bytes2hex(bytes: Array[Byte]): String = {
    bytes.map("%02x".format(_)).mkString
  }

  def parseData(rawData: ByteString): Bracelet = {
    parseData(rawData.toArray)
  }

  def parseData(rawData: String): Bracelet = {
    parseData(rawData.getBytes())
  }

  def parseData(bytes: Array[Byte]): Bracelet = {
    try {
      val startCode: Array[Byte] = Array(bytes(0), bytes(1))
      val length: String = parseLength(bytes(2))
      val command: Byte = bytes(3)
      val motionState: String = parseMotionState(bytes(4))
      val pulseState: Int = parsePulseState(bytes(5))
      val temperature: Float = parseTemperature(Array(bytes(6), bytes(7), bytes(8), bytes(9)))
      val warning: String = parseWarning(bytes(10))
      val sbp: Int = parsePulseState(bytes(11))
      val dbp: Int = parsePulseState(bytes(12))
      val braceletId: String = parseBraceletId(Array(bytes(13), bytes(14), bytes(15), bytes(16), bytes(17), bytes(18), bytes(19)))
      val endCode: Array[Byte] = Array(bytes(20), bytes(21))
      if (!doVerification(startCode, endCode)) {
        DataParseUtil.LOGGER.error("[DataParseUtil] -> [parseData] -> [invalid format : " + bytes2hex(bytes) + "]")
        return null
      }

      val bracelet = new Bracelet
      bracelet.startCode = startCode
      bracelet.length = length
      bracelet.command = command
      bracelet.motionState = motionState
      bracelet.pulseState = pulseState
      bracelet.temperature = temperature
      bracelet.warning = warning
      bracelet.sbp = sbp
      bracelet.dbp = dbp
      bracelet.braceletId = braceletId
      bracelet.endCode = endCode
      return bracelet
    } catch {
      case e: Exception => {
        LOGGER.error("[DataParseByteUtil] -> [parseData] -> [exception]", e)
      }
    }
    return null
  }

  private def parseLength(data: Byte): String = {
    return String.valueOf(data.toInt)
  }

  private def parseMotionState(data: Byte): String = {
    return String.valueOf(data.toInt)
  }

  private def parsePulseState(data: Byte): Int = {
    return data.toInt
  }

  private def parseTemperature(data: Array[Byte]): Float = {
    return parseFloat(new String(data));
  }

  private def parseWarning(data: Byte): String = {
    return String.valueOf(data.toInt)
  }

  private def parseBraceletId(data: Array[Byte]): String = {
    val sb: StringBuilder = new StringBuilder
    for (b <- data) {
      sb.append(String.valueOf(b))
    }
    return sb.toString
  }

  private def parseToString(data: Array[Byte]): String = {
    return new String(data)
  }

  private def doVerification(startArr: Array[Byte], endArr: Array[Byte]): Boolean = {
    val start: String = parseToString(startArr)
    val end: String = parseToString(endArr)
    if (Constants.START.endsWith(start) && Constants.END.equals(end)) {
      return true
    }
    return false
  }

  def parseFloat(s: String): Float = {
    try {
      return s.toFloat
    } catch {
      case e: Exception => {
        LOGGER.error("[DataParseUtil] -> [parseData] -> [exception]", e)
      }
    }
    0;
  }
}
