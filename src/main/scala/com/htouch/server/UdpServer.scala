package com.htouch.server

/**
 * Created by superleo on 29/5/15.
 */

import java.net.InetSocketAddress

import akka.actor._
import akka.io.{IO, Udp}
import com.htouch.constant.Constants
import com.htouch.domain.Bracelet
import com.htouch.util.DataParseUtil
import org.slf4j.LoggerFactory

object UdpServer extends App {
  implicit val system = ActorSystem(Constants.APP_NAME)
  val logger = LoggerFactory.getLogger(UdpServer.getClass)

  class UdpEchoService(address: InetSocketAddress) extends Actor {
    logger.info("==========[UDP Server Init]==========")
    logger.info("==========[UDP Server Bind Port : " + Constants.PORT + "]==========");
    logger.info("==========[UDP Server Started]==========");

    IO(Udp) ! Udp.Bind(self, address)

    def receive = {
      case Udp.Bound(_) => {
        context.become(ready(sender))
      }
    }

    def ready(socket: ActorRef): Receive = {
      case Udp.Received(data, remote) => {
        logger.info("[UdpServerHandler] -> [channelRead0] -> " + data)
        logger.info("[UdpServerHandler] -> [channelRead0] -> " + DataParseUtil.bytes2hex(data))
        // try to store bracelet
        try {
          val bracelet = DataParseUtil.parseData(data);
          Bracelet.store(bracelet);
          socket ! Udp.Send(Constants.ACT_OK, remote)
        } catch {
          case e: Exception => {
            logger.error("[UdpServerHandler] -> [ready] -> [exception]", e)
            socket ! Udp.Send(Constants.ACT_ERROR, remote)
          }
        }
      }
      case Udp.Unbind => socket ! Udp.Unbind
      case Udp.Unbound => context.stop(self)
    }

  }

  val server = system.actorOf(Props(new UdpEchoService(new InetSocketAddress(Constants.PORT))))
}
