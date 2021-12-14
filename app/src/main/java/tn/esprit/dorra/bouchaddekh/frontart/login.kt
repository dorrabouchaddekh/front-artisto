package tn.esprit.dorra.bouchaddekh.frontart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    lateinit var viewpageradapter:MyViewPagerAdapter //Declare PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewpageradapter= MyViewPagerAdapter(supportFragmentManager)

        this.viewPager.adapter=viewpageradapter  //Binding PagerAdapter with ViewPager
        this.tab_layout.setupWithViewPager(this.viewPager) //Binding ViewPager with TabLayout

        /*  val transaction = supportFragmentManager.beginTransaction()
          transaction.add(R.id.fragmentContainerView, SignUp())
              .commit()*/

    }
}