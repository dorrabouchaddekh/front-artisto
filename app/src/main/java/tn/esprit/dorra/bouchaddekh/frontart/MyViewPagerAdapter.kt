package tn.esprit.dorra.bouchaddekh.frontart

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tn.esprit.dorra.bouchaddekh.frontart.fragments.SignUp
import tn.esprit.dorra.bouchaddekh.frontart.fragments.SignnIn

class MyViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = SignUp()
        } else if (position == 1) {
             fragment = SignnIn()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Sign Up"
        } else if (position == 1) {
            title = "Sign In"
        }
        return title
    }
}