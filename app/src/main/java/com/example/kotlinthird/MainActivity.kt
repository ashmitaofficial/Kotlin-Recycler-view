package com.example.kotlinthird

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MyViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var searchBar: SearchView
    private lateinit var originalList:ArrayList<TodoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.my_recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        searchBar=findViewById(R.id.search)


       searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String?): Boolean {
               TODO("Not yet implemented")
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               newText?.let {
                   filteredList(it)
               }
               return false;
           }

       })


        progressBar.isVisible = true
        recyclerView.isVisible = false

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.myLiveData.observe(this) {
            originalList=it
            recyclerView.adapter = MyAdapter(it, this)

            progressBar.isVisible = false
            recyclerView.isVisible = true

        }


    }

   private fun filteredList(txt:String){

       val filteredList:ArrayList<TodoModel> = ArrayList()
       originalList.forEach{
           it.title?.lowercase()?.contains(txt.lowercase())?.let {status->
               if(status == true){
                   filteredList.add(it)
               }
           }
       }
       recyclerView.adapter = MyAdapter(filteredList, this)




   }
}