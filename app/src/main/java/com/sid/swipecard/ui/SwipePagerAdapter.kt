package com.sid.swipecard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sid.swipecard.data.CardData
import com.sid.swipecard.util.ARG_CARD_DATA

class SwipePagerAdapter(
    val listOfPagerContents: List<CardData>,
    fm: FragmentManager,
    behavior: Int
) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        val fragment = SwipeCardFragment()

        fragment.arguments = Bundle().apply {
            putParcelable(ARG_CARD_DATA, listOfPagerContents.get(position))
        }

        return fragment
    }

    override fun getCount(): Int {
        return listOfPagerContents.size
    }


}