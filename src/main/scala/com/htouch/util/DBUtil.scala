/**
 * Created by superleo on 30/6/15.
 */
package com.htouch.util

import com.htouch.constant.Constants
import scalikejdbc.ConnectionPool

class DBUtil {
  Class.forName(Constants.driver)
  ConnectionPool.singleton(Constants.url, Constants.username, Constants.password)
}
