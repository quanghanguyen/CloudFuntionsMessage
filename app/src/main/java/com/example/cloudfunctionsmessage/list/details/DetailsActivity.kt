package com.example.cloudfunctionsmessage.list.details

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cloudfunctionsmessage.databinding.ActivityDetailsBinding
import com.example.cloudfunctionsmessage.firebaseconnection.AuthConnection.author
import com.example.cloudfunctionsmessage.firebaseconnection.AuthConnection.uid
import com.example.cloudfunctionsmessage.firebaseconnection.FireBaseMessaging
import com.example.cloudfunctionsmessage.messageservice.MyFirebaseMessagingService
import com.example.cloudfunctionsmessage.model.TitleModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityDetailsBinding
    private val detailsViewModel : DetailsViewModel by viewModels()
    private var liked : String? = null
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

        FireBaseMessaging.firebaseMessaging.token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                token = task.result
            })

        initEvents()
        initObserve()
    }

    private fun initObserve() {
        detailsViewModel.saveResult.observe(this) {result ->
            when (result) {
                is DetailsViewModel.SaveResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is DetailsViewModel.SaveResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        binding()
        like()
    }

    private fun binding() {
        intent?.let { bundle ->
            val data = bundle.getParcelableExtra<TitleModel>(KEY_DATA)
            with(detailsBinding) {
                if (data?.user_uid == uid) {
                    like.visibility = View.GONE
                }
                articles.text = data?.title
                author.text = data?.author
                liked = data?.user_uid
                liked?.let { Log.e("Liked UID", it) }
            }
        }
    }

    private fun like() {
        detailsBinding.like.setOnClickListener {
            if (uid != null) {
                liked?.let {
                        it1 ->
                    token?.let { it2 ->
                        if (author != null) {
                            detailsViewModel.save(uid, it1, it2, author)
                        }
                    }
                }
            }
        }
    }
}