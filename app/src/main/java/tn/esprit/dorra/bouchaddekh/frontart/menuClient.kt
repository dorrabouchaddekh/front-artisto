package tn.esprit.dorra.bouchaddekh.frontart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_menu_client.*
import tn.esprit.dorra.bouchaddekh.frontart.fragments.*

class menuClient : AppCompatActivity() {
//    private val homeFragment= HomeFragment()
//    private val quizFragment= QuizFragment()
    private val profileFragment= ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_client)
        val name = intent.getStringExtra(NAME).toString()
        val email = intent.getStringExtra(EMAIL).toString()
        val id = intent.getStringExtra(ID).toString()

        Log.e("Error", name)
        replaceFragment(profileFragment,name,email,id)
        buttonNavigation.setOnNavigationItemSelectedListener{
            when (it.itemId)
            {
                R.id.profile->replaceFragment(profileFragment,name,email,id)
           //     R.id.quiz->replaceFragment(quizFragment,name,email,id)
            //    R.id.account->replaceFragment(accountFragment,name,email,id)

            }
            true
        }

    }
    private fun replaceFragment (fragment: Fragment, name :String, email:String, id:String)
    {
        if(fragment!=null)
        {

            val bundle = Bundle()
            bundle.putString(NAME, name)
            bundle.putString(EMAIL, email)
            bundle.putString(ID, id)
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)


            transaction.commit()
        }

    }



}