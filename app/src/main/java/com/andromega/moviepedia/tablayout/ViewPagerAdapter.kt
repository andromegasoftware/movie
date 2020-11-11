package com.andromega.moviepedia.tablayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}

/*
(fm: FragmentManager, lifecycle: Lifecycle, private var totalTabs: Int): FragmentStateAdapter(fm, lifecycle){

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                // # movie fragment
                val bundle: Bundle
                bundle.putString("fragmentName", "Movies Fragment")
                val movieFragment = MoviesFragment
                movieFragment.arguments = bundle
                return movieFragment
            }
            1 -> {
                // # Tv Series Fragment
                //val bundle = Bundle()
                //bundle.putString("fragmentName", "TV Series Fragment")
                val moviesFragment = TVSeriesFragment
                //moviesFragment.arguments = bundle
                return moviesFragment
            }
            2 -> {
                // # Books Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Kids Fragment")
                val booksFragment = KidsFragment
                booksFragment.arguments = bundle
                return booksFragment
            }

            else -> return DemoFragment()
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }


*/


