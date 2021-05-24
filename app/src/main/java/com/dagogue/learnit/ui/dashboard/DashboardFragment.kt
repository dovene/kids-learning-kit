package com.dagogue.learnit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dagogue.learnit.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    companion object {
        val POSITION_EXTRA = "POSITION_EXTRA"
        fun newInstance(position: Int): DashboardFragment {
            val bundle = Bundle()
            Bundle().putInt(POSITION_EXTRA, position)
            val fragment = DashboardFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
      //  dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
       // })
        return root
    }
}