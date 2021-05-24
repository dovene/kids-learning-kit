package com.dagogue.learnit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dagogue.learnit.R
import com.dagogue.learnit.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_alphabet.*


class FragmentAlphabetViewPager : Fragment() {
    private lateinit var viewPager: ViewPager2
    companion object {
        const val NUM_PAGES = 26
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alphabet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = view.findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Images left navigation
        // Images left navigation
        previous.setOnClickListener(View.OnClickListener {
            var tab = viewPager.currentItem
            if (tab > 0) {
                tab--
                viewPager.currentItem = tab
            } else if (tab == 0) {
                viewPager.currentItem = tab
            }
        })


        // Images right navigatin
        next.setOnClickListener(View.OnClickListener {
            var tab = viewPager.currentItem
            tab++
            viewPager.currentItem = tab
        })
    }



    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = HomeFragment.newInstance(position)
    }

}