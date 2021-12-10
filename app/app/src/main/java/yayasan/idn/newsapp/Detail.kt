package yayasan.idn.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Detail : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "nama"
        const val EXTRA_DETAIL = "detail"
        const val EXTRA_PHOTO = "image"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionbar = supportActionBar
        actionbar!!.title= "Detail"
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvDetail: TextView = findViewById(R.id.tv_item_detail)
        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)

        val dataImage = intent.getIntExtra(EXTRA_PHOTO,0)
        val dataTitle = intent.getStringExtra(EXTRA_NAME)
        val dataDetail = intent.getStringExtra(EXTRA_DETAIL)

        tvDetail.text = dataTitle
        Glide.with(this)
            .load(dataImage)
            .apply(RequestOptions())
            .into(imgPhoto)
        tvName.text = dataTitle
        tvDetail.text = dataDetail
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}