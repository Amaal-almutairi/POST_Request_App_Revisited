package com.example.post_request_app_revisited

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    private lateinit var user_id: EditText
    private lateinit var user_name: EditText
    private lateinit var location: EditText

    private lateinit var btDelete: Button
    private lateinit var btUpdate: Button
    // private val apiInterface by lazy { APIClient().getClient().create(APIInterface::class.java) }

    //private var celebrityID = 0
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        //  celebrityID = intent.extras!!.getInt("", 0)

        user_id = findViewById(R.id.userid)
        user_name = findViewById(R.id.name1)
        location = findViewById(R.id.location1)


        btUpdate = findViewById(R.id.btnupdate)

        btUpdate.setOnClickListener {
            // Make sure to add a confirmation alert here
            var f = Users.UserDetails(user_name.text.toString(), location.text.toString(),user_id.text.toString())
            updateusers(f, onResult = {
                user_name.setText("")
                location.setText("")
                user_id.setText("")
                Toast.makeText(applicationContext, "Update Success!", Toast.LENGTH_SHORT).show();
            })
        }
        btDelete = findViewById(R.id.btndelete)

        btDelete.setOnClickListener {
            var f = Users.UserDetails(user_name.text.toString(), location.text.toString(),user_id.text.toString())
            deleteusers (f, onResult = {
                user_name.text
                location.text
                user_id.text
                Toast.makeText(applicationContext, "Delete Success!", Toast.LENGTH_SHORT).show();
            })

        }




    }
    private fun updateusers(f: Users.UserDetails, onResult: () -> Unit){
        progressDialog.show()

        APIInterface.updateusers(user_id).enqueue(object: Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity2, "Celebrity Updated", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity2, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }


    private fun deleteusers(f: Users.UserDetails, onResult: () -> Unit){
        progressDialog.show()

        APIInterface.deleteusers(user_id.text.toString().toInt()).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity2, "Celebrity Deleted", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity2, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun getUser(f: Users.UserDetails, onResult: () -> Unit) {


        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity2)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.getUser((user_id.text.toString().toInt()))
                ?.enqueue(object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {
                        Toast.makeText(applicationContext, "Delete Success!", Toast.LENGTH_LONG)
                            .show()

                        progressDialog.dismiss()

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(
                            this@MainActivity2,
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                })
        }
    }


    fun update_delete(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity2::class.java)
        startActivity(intent)
    }


    fun viewusers(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}




