package ru.rut.permissiondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.rut.permissiondemo.model.Hero

class HeroAdapter(private val baseContext: Context, private val heroList: MutableList<Hero>): RecyclerView.Adapter<HeroAdapter.HeroHolder>() {
    class HeroHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val name: TextView = itemView.findViewById(R.id.name)
        val realname: TextView = itemView.findViewById(R.id.realname)
        val team: TextView = itemView.findViewById(R.id.team)
        val date: TextView = itemView.findViewById(R.id.date)
        val author: TextView = itemView.findViewById(R.id.author)
        val publisher: TextView = itemView.findViewById(R.id.publisher)
        val bio: TextView = itemView.findViewById(R.id.bio)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return HeroHolder(itemView)
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.name.text = heroList.get(position).name
        holder.realname.text = heroList.get(position).realname
        holder.team.text = heroList.get(position).team
        holder.date.text = heroList.get(position).firstAppearanca
        holder.author.text = heroList.get(position).createdBy
        holder.publisher.text = heroList.get(position).publisher
        holder.bio.text = heroList.get(position).bio

        println(heroList.get(position).name)
        println(heroList.get(position).realname)
        println(heroList.get(position).team)
        println(heroList.get(position).firstAppearanca)
        println(heroList.get(position).imageURL)
        println(heroList.get(position).createdBy)
        println(heroList.get(position).publisher)
        println(heroList.get(position).bio)
    }

    override fun getItemCount() = heroList.size
}