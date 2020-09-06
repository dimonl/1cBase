package by.service.tp.OneCBase.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.service.tp.OneCBase.data.QuestList
import by.service.tp.OneCBase.data.Question
import by.service.tp.OneCBase.data.Section
import by.service.tp.OneCBase.ui.challenge.ChallengeElementFragment


class ViewPagerAdapter(activity: Fragment) : FragmentStateAdapter(activity) {

    private val questions = mutableListOf<Question>()
    private var questionsList = mutableListOf<QuestList>()

    fun addQuestions(newQuestions: List<Question>) {
        questions.clear()
        questions.addAll(newQuestions)
        questionsList = makeQuestList()
        notifyDataSetChanged()
    }

    fun makeQuestList() : MutableList<QuestList>{
        val tempList = mutableListOf<QuestList>()
        for(el in questions){
            val currentItemCheck = QuestList(el._id_theme.toString() + "," + el._id_section.toString() +","+ el._id_question.toString())
            if(!tempList.contains(currentItemCheck)){
                tempList.add(currentItemCheck)
            }
        }
        return tempList
    }

    fun getQuestion(pos: Int)  : MutableList<Question> {
        val temp = questionsList[pos]
        val tempList = mutableListOf<Question>()
        for(el in questions){
            val el_temp = el._id_theme.toString() + "," + el._id_section.toString() +","+ el._id_question.toString()
            if( el_temp == temp.name){
                tempList.add(el)
            }
        }
        return tempList
    }

    fun getAnswers(question: MutableList<Question>)  : ArrayList<String> {
        val tempList = ArrayList<String>()
        for(el in question){
            tempList.add(el.nameQuestion)
        }
        return tempList
    }

    fun getChoice(question: MutableList<Question>)  : Int {
        var el_temp = 0

        for(el in question){
            if (el.rightChoice == 1) {
                el_temp = el.orderQuestion
            }

        }
        return el_temp
    }

    override fun createFragment(position: Int): Fragment = ChallengeElementFragment().apply {
        val question = getQuestion(position)
        val answers  = getAnswers(question)
        val rightChoice  = getChoice(question)

        arguments = bundleOf(
            "position" to position,
            "size" to questionsList.size,
            "questionText" to question[0].nameSection,
            "answers" to answers,
            "rightChoice" to rightChoice
        )
    }
    override fun getItemCount(): Int = questionsList.size
}