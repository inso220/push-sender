package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.gson.Gson
import java.io.FileInputStream


fun main() {

    val post = Post(
        author = "Bob",
        content = """
            line 1
            line 2
            line 3
        """.trimMargin()
    )

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "POST")
        .putData("postContent", Gson().toJson(post))
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
