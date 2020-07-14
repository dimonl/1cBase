package by.service.tp.OneCBase.ui.challenge

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import by.service.tp.OneCBase.MyApp
import by.service.tp.OneCBase.R
import by.service.tp.OneCBase.adapters.ViewPagerAdapter
import by.service.tp.OneCBase.data.Question
import by.service.tp.OneCBase.data.QuestionDAO
import by.service.tp.OneCBase.data.Section
import by.service.tp.OneCBase.data.SectionDAO
import kotlinx.android.synthetic.main.fragment_challenge.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengeFragment : Fragment() {
    private var param1: Int? = null
    private var param2: IntArray? = null
    private val uiScope = CoroutineScope(Dispatchers.IO)
    private var list: List<Question> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt("themeIndex")
            param2 = it.getIntArray("sectionsIds")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_challenge, container, false )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val elementAdapter = ViewPagerAdapter(this);

        //uiScope.launch(Dispatchers.Unconfined) {
           Thread {
               getQuestions(param1 ?: 0, param2?: IntArray(0) )
               elementAdapter.addQuestions(list) // items
           }

        //}
        viewPager2Challenge.adapter = elementAdapter
    }

    @WorkerThread
    private fun getQuestions(_idTheme: Int, _idSections: IntArray) {
        val list = QuestionDAO.getQuestions(_idTheme, _idSections).toMutableList()
        Handler(Looper.getMainLooper()).post {
            this.list = list
        }
        //return true  //withContext(Dispatchers.IO) { QuestionDAO.getQuestions(_idTheme, _idSections)}
    }
}