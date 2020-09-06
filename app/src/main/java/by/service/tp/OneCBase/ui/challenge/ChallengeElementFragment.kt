package by.service.tp.OneCBase.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import by.service.tp.OneCBase.R
import kotlinx.android.synthetic.main.challenge_page.*

class ChallengeElementFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.challenge_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var answers: ArrayList<String> = ArrayList()
        arguments?.let {
            questionNumber.text = "вопрос № ${it.getInt("position")+1} " + "из ${it.getInt("size")+1}"
            questionText.text = "Q: ${it.getString("questionText")}"
            answers = it.getStringArrayList("answers") as ArrayList<String>
            val rightchoice = it.getInt("rightChoice")
        }

        for (el in answers) {
            var radio_butt = RadioButton(context)
            radio_butt.setText(el)
            radioGroup.addView(radio_butt)
        }
    }
}