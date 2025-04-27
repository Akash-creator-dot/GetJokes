package com.example.getjokes

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCalls {
    // This function takes a Context and callback function
    // as a parameter, which will be called
    // when the API response is received.
    fun getjokes(context: Context,callback:(DataClass)->Unit){
        // Create a Retrofit instance with the base URL and
        // a GsonConverterFactory for parsing the response.
      val retrofit:Retrofit=Retrofit.Builder().baseUrl("https://api.chucknorris.io/").addConverterFactory(GsonConverterFactory.create()).build()
        // Create an ApiService instance from the Retrofit instance.
        val service:ApiService=retrofit.create<ApiService>(ApiService::class.java)
        // Call the getjokes() method of the ApiService
        // to make an API request.
        val call:Call<DataClass> =service.getjokes()
        // Use the enqueue() method of the Call object to
        // make an asynchronous API request.
        call.enqueue(object :Callback<DataClass>{
            // This is an anonymous inner class that implements the Callback interface.
            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                // This method is called when the API response is received successfully.
                if(response.isSuccessful){
                    // If the response is successful, parse the
                    // response body to a DataModel object.
                    val jokes:DataClass=response.body() as DataClass
                    // Call the callback function with the DataModel
                    // object as a parameter.
                    callback(jokes)
                }
            }

            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                // This method is called when the API request fails.
                Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
            }

        })

    }
}