package com.example.githubsearch.view

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubsearch.MyApp
import com.example.githubsearch.R
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.viewmodel.UserProfileViewModel
import com.example.githubsearch.viewmodel.ViewModelFactory
import javax.inject.Inject

class UserProfileActivity : AppCompatActivity() {


    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UserProfileViewModel

    lateinit var tvUsername: TextView
    lateinit var tvType: TextView
    lateinit var tvScore: TextView
    lateinit var tvProfileUrl: TextView
    lateinit var imgAvatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvType = findViewById<TextView>(R.id.tvType)
        tvScore = findViewById<TextView>(R.id.tvScore)
        tvProfileUrl = findViewById<TextView>(R.id.tvProfileUrl)
        imgAvatar = findViewById<ImageView>(R.id.imgAvatar)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon?.setTint(Color.WHITE)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserProfileViewModel::class.java]

        val userId = intent.getIntExtra("userID", -1)
        if (userId != -1) {
            viewModel.loadUser(userId)
        }

        viewModel.user.observe(this) { user ->
            loadUserData(user)
        }

    }

    private fun loadUserData(user: UserEntity) {

        tvUsername.text = user.login
        tvType.text = user.type
        tvScore.text = user.score.toString()
        Glide.with(this).load(user.avatar_url).into(imgAvatar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}