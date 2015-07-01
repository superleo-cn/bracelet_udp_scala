package com.htouch.domain

import com.htouch.util.DataParseUtil
import org.scalatest.FunSpec

class BraceletSpec extends FunSpec {

  val HEX_VALUE = new String(Array[Byte](0x55, 0x33, 0x10, 0x01, 0x01, 0x3C, 0x33, 0x38, 0x2E, 0x35, 0x01, 0x08, 0x10, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x0D, 0x0A))

  describe("Parse Bracelet") {

    it("should pasrse failed") {
      var bracelet = DataParseUtil.parseData("error string")
      assert(bracelet == null)
    }

    it("should pasrse success") {
      var bracelet = DataParseUtil.parseData(HEX_VALUE)
      assert(bracelet != null)
      assert(bracelet.braceletId === "1234567")
    }

  }

  describe("Store Bracelet") {
    it("should store success") {
      val bracelet = new Bracelet
      bracelet.braceletId = "1234567"
      bracelet.motionState = "1"
      bracelet.pulseState = 100
      bracelet.temperature = 37.8f
      bracelet.warning = "0"
      bracelet.sbp = 130
      bracelet.dbp = 20
      Bracelet.store(bracelet)
    }

  }


}
