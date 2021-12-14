package tn.esprit.dorra.bouchaddekh.frontart.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.dorra.bouchaddekh.frontart.Api.UserApi
import tn.esprit.dorra.bouchaddekh.frontart.R
import tn.esprit.dorra.bouchaddekh.frontart.Utils.RetrofitClient
import tn.esprit.dorra.bouchaddekh.frontart.menuClient


class ChangePassword : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_change_password, container, false)
        return view
    }

}