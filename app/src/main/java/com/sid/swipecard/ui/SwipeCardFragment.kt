package com.sid.swipecard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sid.swipecard.R
import com.sid.swipecard.data.CardData
import com.sid.swipecard.util.ARG_CARD_DATA
import kotlinx.android.synthetic.main.fragment_swipe_card.*

class SwipeCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_swipe_card, container, false)
    }

    // This is the fragment for the viewpager where we add the text file from Parcelable arguments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_CARD_DATA) }?.apply {
            title_text.text = getParcelable<CardData>(ARG_CARD_DATA)!!.text
        }

    }
}