package yayasan.idn.newsapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews:RecyclerView
    private var list:ArrayList<News> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.ahmad_dahlan))
        imageList.add(SlideModel(R.drawable.ki_hadjar_dewantara))
        imageList.add(SlideModel(R.drawable.bung_tomo))
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        rvNews = findViewById(R.id.rvNews)
        rvNews.setHasFixedSize(true)

        list.addAll(NewsData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList(){
        rvNews.layoutManager = LinearLayoutManager(this)
        var listNewsAdapter =  ListNewsAdapter(list)
        rvNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                showResepSelected(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        return true
    }
    private fun showResepSelected(news: News) {
        Toast.makeText(this, "Kamu memilih " + news.name, Toast.LENGTH_SHORT).show()
        val moveData = Intent(this, Detail::class.java)
        moveData.putExtra(Detail.EXTRA_NAME, news.name)
        moveData.putExtra(Detail.EXTRA_PHOTO, news.photo)
        moveData.putExtra(Detail.EXTRA_DETAIL, news.detail)
        startActivity(moveData)
    }

}
