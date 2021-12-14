package tn.esprit.dorra.bouchaddekh.frontart.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.dorra.bouchaddekh.frontart.Api.UserApi
import tn.esprit.dorra.bouchaddekh.frontart.MainActivity
import tn.esprit.dorra.bouchaddekh.frontart.R
import tn.esprit.dorra.bouchaddekh.frontart.Utils.RetrofitClient

class SignUp : Fragment() {
    lateinit var txtUsername: TextInputEditText
    lateinit var txtLayoutUsername: TextInputLayout


    lateinit var txtEmail: TextInputEditText
    lateinit var txtLayoutEmail: TextInputLayout

    lateinit var txtConfirmPassword: TextInputEditText
    lateinit var txtLayoutConfirmPassword: TextInputLayout

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout


    lateinit var btnRegister: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(tn.esprit.dorra.bouchaddekh.frontart.R.layout.fragment_sign_up, container, false)
        btnRegister=view.findViewById(R.id.register)
        txtEmail=view.findViewById(R.id.txtEmail)
        txtConfirmPassword=view.findViewById(R.id.pwdconfirme)
        txtPassword=view.findViewById(R.id.pwd)

        txtUsername=view.findViewById(R.id.txtUsername)






        btnRegister.setOnClickListener{

            doLogin()
        }
        return view
    }

    private fun doLogin() {
        val paramObject1 = JSONObject()
        paramObject1.put("email", txtEmail.text.toString().trim())
        paramObject1.put("password", txtPassword.text.toString().trim())
        paramObject1.put("name", txtUsername.text.toString().trim())

        val jsonParser = JsonParser()
        var gsonObject1 = jsonParser.parse(paramObject1.toString()) as JsonObject
        val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
        retro.signup(gsonObject1).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("Error", t.message.toString())
                Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
            }
        })
    }


}