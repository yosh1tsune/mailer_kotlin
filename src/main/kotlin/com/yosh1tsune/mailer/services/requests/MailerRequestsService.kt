package com.yosh1tsune.mailer.services.requests

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody

class MailerRequestsService(val body: String) {
  val CLIENT = OkHttpClient().newBuilder().build()
  val URL: String = "https://send.api.mailtrap.io/api/send";
  val TOKEN: String = "Bearer a5670b1b971f32fb8882a68271733bc4";
  val MEDIA_TYPE = "application/json"

  fun call() {
    val result = CLIENT.newCall(request()).execute();
    print(result)
  }

  fun request() : Request {
    return Request.Builder()
      .url(URL)
      .method("POST", body.toRequestBody(MEDIA_TYPE.toMediaType()))
      .addHeader("Authorization", TOKEN)
      .addHeader("Content-Type", "application/json")
      .build();
  }
}
