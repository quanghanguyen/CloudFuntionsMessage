package com.example.cloudfunctionsmessage.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloudfunctionsmessage.R
import com.example.cloudfunctionsmessage.databinding.ActivityListBinding
import com.example.cloudfunctionsmessage.list.details.DetailsActivity
import com.example.cloudfunctionsmessage.model.TitleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private lateinit var listBinding : ActivityListBinding
    private lateinit var listAdapter: ListAdapter
    private val listViewModel : ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = ActivityListBinding.inflate(layoutInflater)
        setContentView(listBinding.root)

        initList()
        initObserve()
        listViewModel.load()
    }

    private fun initObserve() {
        listViewModel.loadList.observe(this) {result ->
            when (result) {
                is ListViewModel.LoadList.ResultOk -> {
                    listAdapter.addNewData(result.list)
                }
                is ListViewModel.LoadList.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initList() {
        listBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            listAdapter = ListAdapter(arrayListOf())
            adapter = listAdapter

            listAdapter.setOnItemClickListerner(object :
            OnItemClickListerner {
                override fun onItemClick(requestData: TitleModel) {
                    DetailsActivity.startDetails(context, requestData)
                }
            })
        }
    }
}