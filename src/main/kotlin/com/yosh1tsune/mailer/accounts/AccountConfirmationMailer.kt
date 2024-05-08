package com.yosh1tsune.mailer.accounts

import org.json.JSONObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody

public class AccountConfirmationMailer(val payload: JSONObject) {
    val URL = "https://send.api.mailtrap.io/api/send"
    val SUBJECT = "E-mail de confirmação de conta."
    val MEDIA_TYPE = "application/json"
    val client = OkHttpClient().newBuilder().build()

    fun deliver() {
      client.newCall(request()).execute();
    }

    fun request() : Request {
      return Request.Builder()
        .url(URL)
        .method("POST", body().toRequestBody(MEDIA_TYPE.toMediaType()))
        .addHeader("Authorization", "Bearer a5670b1b971f32fb8882a68271733bc4")
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
            "subject": "${SUBJECT}",
            "text": "${payload.get("confirmation_url")}",
            "category": "Confirmação de Conta"
          }
        """.replace("\n", "")
    }
}
