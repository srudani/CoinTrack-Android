package com.example.cointrack

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cointrack.databinding.CoinListItemLayoutBinding

interface CoinListAdapterDelegate {
    fun onCoinSelected(coin: CoinListResponse.CoinListResponseItem)
}

class CoinListAdapter(var data: ArrayList<CoinListResponse.CoinListResponseItem>, private val delegate: CoinListAdapterDelegate) : RecyclerView.Adapter<CoinListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binder = DataBindingUtil.inflate<CoinListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.coin_list_item_layout,
            parent,
            false
        )
        return CoinListViewHolder(binder)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvCoinName.text = item.name
        holder.binding.tvPosition.text = item.market_cap_rank.toString()
        Glide.with(holder.binding.root.context).load(item.image).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false).into(holder.binding.ivCoinImage)
        holder.binding.tvCoinPrice.text = "$${String.format("%.2f",item.current_price)}"
        holder.binding.tvCoinPer.text = "${String.format("%.2f",item.price_change_percentage_24h)}%"
        if (item.price_change_percentage_24h > 0){
            holder.binding.tvCoinPer.setTextColor(Color.GREEN)
        }else{
            holder.binding.tvCoinPer.setTextColor(Color.RED)
        }

        holder.itemView.setOnClickListener {
            delegate.onCoinSelected(item)
        }

    }
}

class CoinListViewHolder(val binding: CoinListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)