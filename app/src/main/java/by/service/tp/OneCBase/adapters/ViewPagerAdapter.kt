package by.service.tp.OneCBase.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.service.tp.OneCBase.data.Question
import by.service.tp.OneCBase.data.Section
import by.service.tp.OneCBase.ui.challenge.ChallengeElementFragment


class ViewPagerAdapter(activity: Fragment) : FragmentStateAdapter(activity) {

    private val questions = mutableListOf<Question>()
//    private val colors = intArrayOf(
//        android.R.color.black,
//        android.R.color.holo_red_light,
//        android.R.color.holo_blue_dark,
//        android.R.color.holo_purple
//    )
    init {

    }

    fun addQuestions(newQuestions: List<Question>) {
        questions.clear()
        questions.addAll(newQuestions)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment = ChallengeElementFragment().apply {
        arguments = bundleOf(
//            "color" to colors[position],
            "position" to position
        )
    }
    override fun getItemCount(): Int = questions.size
}