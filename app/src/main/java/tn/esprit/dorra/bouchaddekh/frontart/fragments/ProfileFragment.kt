package tn.esprit.dorra.bouchaddekh.frontart.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import androidx.fragment.app.FragmentTransaction
import tn.esprit.dorra.bouchaddekh.frontart.fragments.*
import tn.esprit.dorra.bouchaddekh.frontart.R


class ProfileFragment : Fragment() {
    lateinit var editeProfile :ImageView
    lateinit var changePass : ImageView
    lateinit var logout : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view =  inflater.inflate(R.layout.fragment_profile, container, false)
/*val imageback = view.findViewById<ImageView>(R.id.back)
        imageback.setOnClickListener{
            val home =  HomeFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container,home).commit()
        }*/
        editeProfile = view.findViewById(R.id.editProfile)
        changePass = view.findViewById(R.id.ChangePassword)
        logout = view.findViewById(R.id.imageViewlogoutapp)

        val name=requireArguments().getString(NAME,"NULL")
        val email=requireArguments().getString(EMAIL,"NULL")
        val id=requireArguments().getString(ID,"NULL")


        Log.e("Error", name.toString())
        editeProfile.setOnClickListener{
            val modifprofile=  ModifyProfile()
            val bundle = Bundle()
            bundle.putString(NAME, name)
            bundle.putString(EMAIL, email)
            bundle.putString(ID, id)
            modifprofile.arguments = bundle
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()

            transaction.replace(R.id.fragment_container,modifprofile).commit()
        }
        changePass.setOnClickListener{
            val changePassword =  ChangePassword()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container,changePassword).commit()
        }
        logout.setOnClickListener{
            val signIn =  SignnIn()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container,signIn).commit()
        }


        return  view
    }


}