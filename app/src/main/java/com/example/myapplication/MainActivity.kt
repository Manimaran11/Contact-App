package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.os.AsyncTask
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import org.bson.Document
import org.json.JSONArray
import org.json.JSONObject


//import sun.jvm.hotspot.utilities.IntArray

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val textis: TextView = findViewById(R.id.mobile_list)
//        textis.text = "Changed Text"
//        textis.setOnClickListener {
//            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
//        }
        var dataList = ArrayList<HashMap<String, String>>()
        val result = mutableListOf<Document>()
        findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
        val stitchAppClient = Stitch.initializeAppClient(resources.getString(R.string.stitchapp))
        stitchAppClient.auth.loginWithCredential(AnonymousCredential())
            .addOnSuccessListener {
                val mongoClient = stitchAppClient.getServiceClient(
                    RemoteMongoClient.factory,
                    "mongodb-atlas"
                )
                Log.d("dbConnection", "success")
                val myCollection = mongoClient.getDatabase("contacts")
                    .getCollection("ct_contacts")
                val query = myCollection.find()
                    .sort(Document("first_name", 1))

                query.into(result).addOnSuccessListener {
                    Log.d("result", result.toString())


                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

//                    val jsonObj =
                    val usersArr = JSONArray(result)
                    for (i in 0 until usersArr.length()) {
                        val singleUser = usersArr.getJSONObject(i)

                        val map = HashMap<String, String>()
                        map["first_name"] = singleUser.getString("first_name")
                        map["last_name"] = singleUser.getString("last_name")
                        map["work_number"] = singleUser.getString("work_number")
                        dataList.add(map)
                        Log.d("after process",singleUser.toString())
                    }

                    findViewById<ListView>(R.id.listView).adapter = CustomAdapter(this@MainActivity, dataList)

                }
            }

//                fetchJsonData().execute()
    }


    inner class fetchJsonData() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            Log.d("test","hi")

                    return ""
                }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("Fetched Data", result)
        }
    }
}
