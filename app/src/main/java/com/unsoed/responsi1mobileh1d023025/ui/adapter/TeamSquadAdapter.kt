package com.unsoed.responsi1mobileh1d023025.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023025.databinding.ItemPlayerBinding
import com.unsoed.responsi1mobileh1d023025.data.model.Player

class TeamSquadAdapter(
    private var playerList: List<Player> = emptyList(),
    private val onPlayerClicked: (Player) -> Unit
) : RecyclerView.Adapter<TeamSquadAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.binding.textPlayerName.text = player.name ?: "-"
        holder.binding.textPlayerNationality.text = player.nationality ?: "-"

        val playerPosition = player.position?.lowercase() ?: "unknown"

        val color = when {
            playerPosition.contains("goalkeeper") -> Color.parseColor("#FFF176")
            playerPosition.contains("defence") || playerPosition.contains("back") -> Color.parseColor("#64B5F6")
            playerPosition.contains("midfield") -> Color.parseColor("#81C784")
            playerPosition.contains("forward") || playerPosition.contains("attacker") || playerPosition.contains("striker") || playerPosition.contains("winger") || playerPosition.contains("offence") -> Color.parseColor("#E57373")
            else -> Color.parseColor("#E0E0E0")
       }

        holder.binding.root.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            onPlayerClicked(player)
        }
    }

    fun setPlayers(players: List<Player>) {
        playerList = players
        notifyDataSetChanged()
    }
}