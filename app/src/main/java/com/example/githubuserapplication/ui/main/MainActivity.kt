package com.example.githubuserapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapplication.data.model.User
import com.example.githubuserapplication.databinding.MainActivityBinding
import com.example.githubuserapplication.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object:UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity,DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener {

            }

            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}