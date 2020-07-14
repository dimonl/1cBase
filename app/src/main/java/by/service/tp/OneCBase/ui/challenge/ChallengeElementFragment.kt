package by.service.tp.OneCBase.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.service.tp.OneCBase.R
import kotlinx.android.synthetic.main.challenge_page.*

class ChallengeElementFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.challenge_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            questionText.text = "Item ${it.getInt("position")} " + "its a fragment"
        }
    }
}