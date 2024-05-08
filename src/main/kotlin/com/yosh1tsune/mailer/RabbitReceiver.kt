package com.yosh1tsune.mailer

import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import org.json.JSONObject

import com.yosh1tsune.mailer.accounts.AccountConfirmationMailer

@Service
@RabbitListener(queues = ["accounts.confirmation"])
class RabbitReceiver {
  @RabbitHandler
  fun receive(message: ByteArray) {
    val payload = JSONObject(message.toString(Charsets.UTF_8))
    AccountConfirmationMailer(payload).deliver()
  }
}
