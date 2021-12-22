package ice_and_fire_api.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.rut.api.R
import ice_and_fire_api.api.model.Hero

class HeroAdapter(private val baseContext: Context, private val heroList: MutableList<Hero>): RecyclerView.Adapter<HeroAdapter.HeroHolder>() {
    class HeroHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val name: TextView = itemView.findViewById(R.id.name)
        val culture: TextView = itemView.findViewById(R.id.culture)
        val born: TextView = itemView.findViewById(R.id.born)
        val titles: TextView = itemView.findViewById(R.id.titles)
        val aliases: TextView = itemView.findViewById(R.id.aliases)
        val playedBy: TextView = itemView.findViewById(R.id.playedBy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return HeroHolder(itemView)
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.name.text = heroList.get(position).name
        holder.culture.text = heroList.get(position).culture
        holder.born.text = heroList.get(position).born
        holder.titles.text = heroList.get(position).titles?.joinToString(", ") ?: "None"
        holder.aliases.text = heroList.get(position).aliases?.joinToString(", ") ?: "None"
        holder.playedBy.text = heroList.get(position).playedBy?.joinToString(", ") ?: "None"
    }

    override fun getItemCount() = heroList.size
}