package com.yosh1tsune.mailer.mailers.accounts

import org.json.JSONObject

import com.yosh1tsune.mailer.services.requests.MailerRequestsService

public class AccountConfirmationMailer(val payload: JSONObject) {
    val SUBJECT = "E-mail de confirmação de conta."
    val TEMPLATE_UUID = System.getenv("MAILER_ACCOUNT_CONFIRMATION_TEMPLATE")

    fun deliver() {
      MailerRequestsService(body()).call()
    }

    fun body() : String {
      return """
        {
          "from": {
            "email": "mailtrap@demomailtrap.com",
            "name": "Mailtrap Test"
          },
          "to": [{ "email": "${payload.get("email")}" }],
          "template_uuid": "${TEMPLATE_UUID}",
          "template_variables": {
            "subject": "${SUBJECT}",
            "name": "${payload.get("name")}",
            "confirmation_url": "${payload.get("confirmation_url")}"
          }
        }
        """.replace("\n", "")
    }
}
