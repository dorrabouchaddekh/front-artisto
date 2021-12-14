package tn.esprit.dorra.bouchaddekh.frontart.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import tn.esprit.dorra.bouchaddekh.frontart.fragments.ProfileFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.dorra.bouchaddekh.frontart.Api.UserApi
import tn.esprit.dorra.bouchaddekh.frontart.R
import tn.esprit.dorra.bouchaddekh.frontart.Utils.RetrofitClient


class ModifyProfile : Fragment() {

    lateinit var buttonBack : ImageView
    lateinit var update : Button
    lateinit var phonetxt : TextInputEditText
    lateinit var usernametxt : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_modify_profile, container, false)
        buttonBack = view.findViewById(R.id.account_back)
        phonetxt= view.findViewById(R.id.phone)
        usernametxt= view.findViewById(R.id.username)
        update= view.findViewById(R.id.update)

        val name=requireArguments().getString(NAME,"NULL")
        val email=requireArguments().getString(EMAIL,"NULL")
        val id=requireArguments().getString(ID,"NULL")
        Log.e("Error", name.toString())
        Log.e("Error", email.toString())

        show(email)
        update.setOnClickListener{
            update(id)
        }

        buttonBack.setOnClickListener {
            val account = ProfileFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container, account).commit()


              }
        return view
    }


fun show (email:String)
{

    val paramObject1 = JSONObject()
    paramObject1.put("email",email.substring(1,email.length-1))

  // Log.i("Error",email.substring(1,email.length-1))
   // paramObject1.put("password", txtPassword.text.toString().trim())
    val jsonParser = JsonParser()
    var gsonObject1 = jsonParser.parse(paramObject1.toString()) as JsonObject
    val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
    retro.showUser(gsonObject1).enqueue(object :Callback<JsonObject> {


        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

            Toast.makeText(context, "show yes", Toast.LENGTH_SHORT).show()
            Log.i("Error", response.body()?.get("phone").toString())
            Log.e("Error",response.body()?.get("username").toString())
            phonetxt.setText(response.body()?.get("phone").toString())
            val username=response.body()?.get("username").toString()
            usernametxt.setText(username.substring(1,username.length-1))

           // ID=response.body()?.existingUser?.id.toString()

        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
        }
    })

}
    fun update(id:String) {

        val paramObject1 = JSONObject()
        paramObject1.put("_id", id.substring(1, id.length - 1))
        paramObject1.put("phone", phonetxt.text.toString().trim())
        paramObject1.put("username", usernametxt.text.toString().trim())

        val jsonParser = JsonParser()
        var gsonObject1 = jsonParser.parse(paramObject1.toString()) as JsonObject
        val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
        retro.updateUser(gsonObject1).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Toast.makeText(context, "update yes", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(context, "not update", Toast.LENGTH_SHORT).show()
            }
        })

    }
    /*fun update (email: String)
    {
        val req = UserRequest()
        req.email=email.toString()
       // req.phone=phonetxt.text.toString()

        req.username=usernametxt.text.toString()




        Log.e("Error",email)

        val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
        retro.updateUser(req).enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.e("Error", response.code().toString())
               // Log.e("Error", response.body()?.existingUser?.username.toString())
                Toast.makeText(context, "update yes", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "not update", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
}