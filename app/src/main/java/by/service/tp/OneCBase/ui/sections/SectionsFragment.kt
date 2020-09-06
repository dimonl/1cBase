package by.service.tp.OneCBase.ui.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.service.tp.OneCBase.R
import by.service.tp.OneCBase.adapters.SectionsAdapter
import by.service.tp.OneCBase.adapters.ThemesAdapter
import by.service.tp.OneCBase.data.Section
import by.service.tp.OneCBase.data.SectionDAO
import by.service.tp.OneCBase.data.Theme
import by.service.tp.OneCBase.data.ThemeDAO
import kotlinx.android.synthetic.main.fragment_sections.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Unconfined

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThemesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThemesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = 0

    private val uiScope = CoroutineScope(Dispatchers.IO)

    private lateinit var sectionsAdapter: SectionsAdapter
    var list = mutableListOf<Section>()
    var choosenItems: MutableSet<Int> = mutableSetOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param3 = it.getInt("themeIndex") + 1
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sections, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sectionsAdapter = SectionsAdapter()

        uiScope.launch(IO) {
            list = getSections(param3 ?: 0).toMutableList()
            sectionsAdapter.addSections(list) // items
            RecyclerViewListSections.adapter = sectionsAdapter
        }
        RecyclerViewListSections.layoutManager = LinearLayoutManager(this.context)



        sectionsAdapter.onItemClick = {
            if (!it.isChecked) {
                choosenItems.add(it._id)
            } else {
                choosenItems.remove(it._id)
            }
            //view?.findNavController()?.navigate(R.id.themesFragment, savedInstanceState?:newBundle )
        }


         swCheckAll.setOnCheckedChangeListener { buttonView, isChecked ->
            list.map {
                it.isChecked = isChecked
                if (isChecked) {
                    choosenItems.add(it._id)
                } else {
                    choosenItems.remove(it._id)
                }
            }
             sectionsAdapter.notifyDataSetChanged()
                          //sectionsAdapter.changeSections(isChecked)
         }

        btnStartChallenge.setOnClickListener {
            val newBundle = Bundle()
            savedInstanceState?.putIntArray("sectionsIds", choosenItems.toIntArray()) ?: run {
                newBundle.putIntArray("sectionsIds", choosenItems.toIntArray())
                newBundle.putInt("themeIndex", param3 ?: 0)
            }
            view?.findNavController()?.navigate(R.id.challengeFragment, savedInstanceState?:newBundle )
        }
    }

    private suspend fun getSections(_idTheme: Int): List<Section> { //
        return withContext(Dispatchers.IO) { SectionDAO.getAllSections(_idTheme)}
    }

    override fun onDestroyView() {
        uiScope.cancel()
        super.onDestroyView()
    }
}

