package tn.esprit.dorra.bouchaddekh.frontart.fragments


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import tn.esprit.dorra.bouchaddekh.frontart.data.User
import tn.esprit.dorra.bouchaddekh.frontart.menuClient

const val PREF_NAME = "LOGIN_FACTCKECH"
const val IS_REMEMBRED = "IS_REMEMBRED"
const val NAME = "NAME"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IMAGE = "IMAGE"
const val ID = "ID"
class SignnIn : Fragment() {

    lateinit var txtLogin: TextInputEditText
    lateinit var txtLayoutLogin: TextInputLayout

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout


    lateinit var btnLogin: Button
    lateinit var btnHome : Button
    lateinit var cbRememberMe: CheckBox
    lateinit var mSharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        mSharedPref = requireActivity().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE)
        txtLogin =view.findViewById(R.id.txtLogin)
        txtLayoutLogin = view.findViewById(R.id.txtLayoutLogin)

        txtPassword = view.findViewById(R.id.txtpwd)
        txtLayoutPassword = view.findViewById(R.id.txtLayoutPwd)
        cbRememberMe = view.findViewById(R.id.checkBox)
        btnHome = view.findViewById<Button>(R.id.next)
        btnLogin = view.findViewById(R.id.next)

        /*   if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
               navigate()
           }*/



        //  navigate()
        btnLogin.setOnClickListener{

            doLogin()
        }
        return view
    }

    private fun doLogin() {
        /*  if (cbRememberMe.isChecked){
              //TODO 4 "Edit the SharedPreferences by putting all the data"
              Log.e("error","cheked")
              mSharedPref.edit().apply{
                  putBoolean(IS_REMEMBRED, true)
                   putString(EMAIL, txtLogin.text.toString().trim())
                  //   putString(FULL_NAME, txtPassword.text.toString())
              }.apply()

          }else{
              mSharedPref.edit().clear().apply()
          }*/
        val paramObject1 = JSONObject()
        paramObject1.put("email", txtLogin.text.toString().trim())
        paramObject1.put("password", txtPassword.text.toString().trim())

        val jsonParser = JsonParser()
        var gsonObject1 = jsonParser.parse(paramObject1.toString()) as JsonObject
        val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
        retro.signin(gsonObject1).enqueue(object :Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                val id = response.body()?.get("_id").toString()
                val email=response.body()?.get("email").toString()
                val image=response.body()?.get("image").toString()

                val user = response.body()?.get("name").toString()
                val name=user.substring(1,user.length-1)


                val password = response.body()?.get("password").toString()


                val u = User (id,email,image, name, password)

                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, menuClient::class.java)
                startActivity(intent)
//                if (role == "\"utilisateur\"") {
//                    // Log.e("Error", role)
//                    Toast.makeText(context, "utilisateur", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(activity, MainActivity::class.java).apply {
//                        putExtra(ID,id)
//                        putExtra(EMAIL,email)
//                        putExtra(PHONE,phone)
//                        putExtra(IMAGE,image)
//                        putExtra(USER_NAME,username)
//                        putExtra(PASSWORD,password)
//                    }
//
//
//                    startActivity(intent)
//                } else if (role == "\"source\"") {
//                    Toast.makeText(context, "source", Toast.LENGTH_SHORT).show()
//                    //  Log.e("Error", role)
//                    // Log.e("Error",username)
//                    val intent = Intent(activity, MainActivity::class.java)
//                        .apply {
//                            putExtra(ID,id)
//                            putExtra(EMAIL,email)
//                            putExtra(IMAGE,image)
//                            putExtra(USER_NAME,username)
//                            putExtra(PASSWORD,password)
//                        }
//                    startActivity(intent)
//
//
//                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("Error", t.message.toString())
                Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
            }


            /* val req = User()
             req.email=txtLogin.text.toString()
             req.password = txtPassword.text.toString()



             val retro = RetrofitClient().getRetroClinetInstance().create(UserApi::class.java)
             retro.login(req).enqueue(object :Callback<UserResponse>{

                 override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {


                     val user= response.body()
                     Log.e("Error", response.body()?.existingUser?.id.toString())

                     if (response.code() == 200) {

                         Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                         if (response.body()?.existingUser?.role.toString() == "client") {
                             val intent = Intent(activity, menuClient::class.java).apply {
                                 putExtra(FULL_NAME,response.body()?.existingUser?.username.toString())
                                 putExtra(EMAIL,response.body()?.existingUser?.email.toString())
                                 putExtra(ID,response.body()?.existingUser?.id.toString())
                             }

                             startActivity(intent)
                         } else if (response.body()?.existingUser?.role.toString() == "clientSource") {
                             val intent = Intent(activity, menuSource::class.java).apply {
                                 putExtra(FULL_NAME, response.body()?.existingUser?.username.toString())
                                 putExtra(EMAIL, response.body()?.existingUser?.email.toString())
                                 putExtra(ID, response.body()?.existingUser?.id.toString())
                             }
                             startActivity(intent)


                         }
                     }else {
                         Toast.makeText(context, "Password not correct", Toast.LENGTH_SHORT).show()
                     }
                 }

                 override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                     Log.e("Error", t.message.toString())
                     Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                 }


             })*/
        }
        ) }

    private fun navigate(){
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)


    }

}