package com.example.cloudfunctionsmessage.list.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cloudfunctionsmessage.databinding.ActivityDetailsBinding
import com.example.cloudfunctionsmessage.messageservice.MyFirebaseMessagingService
import com.example.cloudfunctionsmessage.model.TitleModel
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityDetailsBinding
    private var token : String? = null

    companion object {
        private const val KEY_DATA = "request_data"
        fun startDetails(context: Context, data : TitleModel?)
        {
            context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(detailsBinding.root)

        binding()
        like()
    }

    private fun like() {
        detailsBinding.like.setOnClickListener {
            FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
                token = task.result
            }
            token?.let { it1 -> FirebaseMessaging.getInstance().subscribeToTopic(it1) }
        }
    }

    private fun binding() {
        intent?.let { bundle ->
            val data = bundle.getParcelableExtra<TitleModel>(KEY_DATA)
            with(detailsBinding) {
                articles.text = data?.title
                author.text = data?.author
            }
        }
    }
}