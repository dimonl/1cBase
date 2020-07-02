package by.service.tp.OneCBase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.service.tp.OneCBase.R
import by.service.tp.OneCBase.data.Theme

class ThemesAdapter(): RecyclerView.Adapter<ThemesAdapter.ThemesViewHolder>() {

    private val themes = mutableListOf<Theme>()

    var onItemClick: ((Theme) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.theme_item, null)

        return ThemesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  themes.size
    }

    override fun onBindViewHolder(holder: ThemesViewHolder, position: Int) {
        holder.bind(themes[position].name, themes[position]._id.toString())
    }

    fun addThemes(newThemes: List<Theme>) {
        themes.addAll(newThemes)
        notifyDataSetChanged()
    }

    inner class  ThemesViewHolder(view: View): RecyclerView.ViewHolder(view)   {


        private val button = view.findViewById<Button>(R.id.btnStartChallenge)
        private val textView = view.findViewById<TextView>(R.id.textViewHidden)


        init {

            button.setOnClickListener {
                onItemClick?.invoke(themes[adapterPosition])
            }

        }


        fun bind(nameButton: String, hiddenText: String ){

            button.text = nameButton
            textView.text = hiddenText
        }


    }
}


