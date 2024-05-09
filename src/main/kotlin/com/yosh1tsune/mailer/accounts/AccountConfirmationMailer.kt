package com.yosh1tsune.mailer.accounts

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody

object MailerConstants {
  val URL: String = @Value("\${mailtrap.url}");
  val TOKEN: String = @Value("\${mailtrap.token}");
  const val SUBJECT = "E-mail de confirmação de conta."
  const val MEDIA_TYPE = "application/json"
}

public class AccountConfirmationMailer(val payload: JSONObject) {
    val client = OkHttpClient().newBuilder().build()

    fun deliver() {
      val result = client.newCall(request()).execute();
    }

    fun request() : Request {
      return Request.Builder()
        .url(MailerConstants.URL)
        .method("POST", body().toRequestBody(MailerConstants.MEDIA_TYPE.toMediaType()))
        .addHeader("Authorization", MailerConstants.TOKEN)
        .addHeader("Content-Type", "application/json")
        .build();
    }

    fun body() : String {
      return """
          {
            "from": {
              "email": "mailtrap@demomailtrap.com",
              "name": "Mailtrap Test"
            },
            "to": [{ "email": "${payload.get("email")}" }],
            "subject": "${MailerConstants.SUBJECT}",
            "text": "${payload.get("confirmation_url")}",
            "category": "Confirmação de Conta"
          }
        """.replace("\n", "")
    }
}
