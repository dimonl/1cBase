package by.service.tp.OneCBase.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.service.tp.OneCBase.MyApp
import by.service.tp.OneCBase.R
import by.service.tp.OneCBase.adapters.ThemesAdapter
import by.service.tp.OneCBase.data.Theme
import by.service.tp.OneCBase.data.ThemeDAO
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() { // , ThemesViewHolder.OnThemeListener
    var navController: NavController? = null
    companion object {
        fun newInstance() = MainFragment()
    }

    private val uiScope = CoroutineScope(Dispatchers.IO)

    private lateinit var themeAdapter: ThemesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
         return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        themeAdapter = ThemesAdapter()

        uiScope.launch(Unconfined) {
            var list = getThemes()
            themeAdapter.addThemes(list) // items
        }

        RecyclerViewListThemes.adapter = themeAdapter
        RecyclerViewListThemes.layoutManager = LinearLayoutManager(this.context)
        themeAdapter.onItemClick = { theme ->
            val newBundle = Bundle()
            savedInstanceState?.putInt("themeIndex", theme._id) ?: run {
                newBundle.putInt("themeIndex", theme._id)
            }
            view?.findNavController()?.navigate(R.id.themesFragment, savedInstanceState?:newBundle )
         }
    }


    private suspend fun getThemes(): List<Theme> {
        return withContext(Dispatchers.IO) { ThemeDAO.getAllThemes() }
    }


}