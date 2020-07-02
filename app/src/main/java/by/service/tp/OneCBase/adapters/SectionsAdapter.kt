package by.service.tp.OneCBase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.service.tp.OneCBase.R
import by.service.tp.OneCBase.data.Section
import by.service.tp.OneCBase.data.Theme
import com.google.android.material.chip.Chip

class SectionsAdapter(): RecyclerView.Adapter<SectionsAdapter.SectionsViewHolder>() {

    private val sections = mutableListOf<Section>()

    var onItemClick: ((Section) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section_item, null)

        return SectionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  sections.size
    }

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        holder.bind(sections[position].isChecked, sections[position].name)
    }

    fun addSections(newSections: List<Section>) {
        sections.clear()
        sections.addAll(newSections)
        notifyDataSetChanged()
    }

    fun changeSections(state: Boolean) {
        sections.map {
            it.isChecked = state
        }
        notifyDataSetChanged()
    }

    fun getSections(): List<Section> {
        return sections
    }

    inner class  SectionsViewHolder(view: View): RecyclerView.ViewHolder(view)   {


        private val switchSection = view.findViewById<Switch>(R.id.switchSection)
        private val textSection = view.findViewById<TextView>(R.id.textSection)


        init {

            switchSection.setOnClickListener {
                onItemClick?.invoke(sections[adapterPosition])
            }
        }
        fun bind(switch: Boolean, nameSection: String ){
            switchSection.isChecked = switch
            textSection.text = nameSection
        }
    }
}


