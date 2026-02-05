package com.example.githubsearch.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.MyApp
import com.example.githubsearch.R
import com.example.githubsearch.adapter.UserListAdapter
import com.example.githubsearch.viewmodel.SearchUserViewModel
import com.example.githubsearch.viewmodel.ViewModelFactory
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchUserViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val etSearchUser = findViewById<EditText>(R.id.etSearchUser)
        val recyclerUser = findViewById<RecyclerView>(R.id.recyclerUser)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon?.setTint(Color.WHITE)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchUserViewModel::class.java)
        viewModel.users.observe(this) {
            users ->
        }

        adapter = UserListAdapter{ user ->
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra("userID", user.id)
            startActivity(intent)
        }

        recyclerUser.layoutManager = LinearLayoutManager(this)
        recyclerUser.adapter = adapter

        viewModel.message.observe(this) {
            AlertDialog.Builder(this)
                .setMessage(it)
                .setPositiveButton("OK", null)
                .show()
        }

        etSearchUser.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchUser(etSearchUser.text.toString())
                true
            } else false
        }

        viewModel.users.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

