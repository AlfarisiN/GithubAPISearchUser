package com.example.githubsearch.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuckerteam.chucker.api.Chucker
import com.example.githubsearch.MyApp
import com.example.githubsearch.R
import com.example.githubsearch.adapter.DashboardAdapter
import com.example.githubsearch.viewmodel.DashboardViewModel
import com.example.githubsearch.viewmodel.ViewModelFactory
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DashboardViewModel

    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        (application as MyApp).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DashboardViewModel::class.java)

        adapter = DashboardAdapter{ item ->
            when (item.title) {
                "Search User" -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvDashboard)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        viewModel.item.observe(this) {
            adapter.submitList(it)
        }

        viewModel.loadDashboard()

    }
}