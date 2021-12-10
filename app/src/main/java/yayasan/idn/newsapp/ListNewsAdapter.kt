package yayasan.idn.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListNewsAdapter(private val ListNews:ArrayList<News>): RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback:OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = ListNews[position]
        Glide.with(holder.itemView.context)
            .load(news.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)

        holder.tvName.text = news.name
        holder.tvDetail.text = news.detail
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(ListNews[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return ListNews.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }
}