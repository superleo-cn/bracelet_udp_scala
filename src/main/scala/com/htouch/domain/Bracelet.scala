package com.htouch.domain

import com.htouch.util.DBUtil
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

import scalikejdbc._

class Bracelet {

  var startCode: Array[Byte] = null

  var length: String = "0"

  var command: Byte = 0

  var motionState: String = "0"

  var pulseState: Int = 0

  var temperature: Float = 0

  var warning: String = "0"

  var sbp: Int = 0

  var dbp: Int = 0

  var braceletId: String = "0"

  var endCode: Array[Byte] = null

}

object Bracelet extends DBUtil {
  val logger = LoggerFactory.getLogger(Bracelet.getClass)

  var INSERT_SQL =
    """
      |INSERT INTO
      | tb_health_data(bracelet_id, motion_state, pulse_state, temperature, warning, sbp, dbp, create_date)
      |VALUES
      | (?, ?, ?, ?, ?, ?, ?, ?)
      |
    """.stripMargin

  def store(bracelet: Bracelet): Unit = {
    using(ConnectionPool.borrow()) { conn: java.sql.Connection =>
      val db: DB = DB(conn)
      db.localTx { implicit session =>
        SQL(INSERT_SQL)
          .bind(bracelet.braceletId,
            bracelet.motionState,
            bracelet.pulseState,
            bracelet.temperature,
            bracelet.warning,
            bracelet.sbp,
            bracelet.dbp,
            DateTime.now)
          .update().apply()
      }
    }
  }

}
