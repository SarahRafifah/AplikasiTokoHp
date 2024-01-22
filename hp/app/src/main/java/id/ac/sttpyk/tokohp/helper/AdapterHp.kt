package id.ac.sttpyk.tokohp.helper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.sttpyk.tokohp.R
import id.ac.sttpyk.tokohp.databinding.ItemHpBinding
import id.ac.sttpyk.tokohp.models.HpModel

class AdapterHp(private val items:ArrayList<HpModel.Data>, val listener: OnAdapterListener):
    RecyclerView.Adapter<AdapterHp.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHpBinding) :RecyclerView.ViewHolder(binding.root) {
        fun init(data: HpModel.Data){
            binding.namahp.text=data.hp
            binding.kapasitas.text=data.harga.toString()
            binding.imgDelete.setOnClickListener{
                onItemClickCallback.onItemClicked(data)
                Log.e("TAG", "init: Tombol di klik",)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hp, parent, false)
        val binding = ItemHpBinding.bind(V)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(items[position])
        holder.itemView.setOnClickListener {
            listener.OnClick(items[position])
        }
    }

    private interface OnItemClickCallback {
        fun onItemClicked(data: HpModel.Data)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(body: (HpModel.Data) -> Unit) {
        onItemClickCallback = object : OnItemClickCallback {
            override fun onItemClicked(data: HpModel.Data){
                body(data)
            }
        }
    }
    interface OnAdapterListener{
        fun OnClick(hp: HpModel.Data)
    }
}