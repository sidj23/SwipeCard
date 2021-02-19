package com.sid.swipecard.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sid.swipecard.R
import com.sid.swipecard.data.CardData
import com.sid.swipecard.data.CardResponse
import com.sid.swipecard.util.api.ApiInterface
import com.sid.swipecard.util.api.ApiUtil
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var apiInterface: ApiInterface? = null
    private var cardList: List<CardData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface::class.java)

        setUpDataSource()
        getCardData()

    }

    private fun setUpDataSource() {
        getCardData()
    }

    // IN this method Viewpager and Tablayout is initiated.

    private fun setUpView() {
        var swipePagerAdapter =
            SwipePagerAdapter(cardList, supportFragmentManager, 0)
        card_view_pager.adapter = swipePagerAdapter
        card_view_pager.offscreenPageLimit = cardList.size - 1

        circle_tab_layout.setupWithViewPager(card_view_pager)

        main_progress_bar.visibility = View.GONE
        main_layout.visibility = View.VISIBLE
    }


    // This method makes the API Call

    fun getCardData() {
        val genreResponseCall: Call<String?>? = apiInterface!!.makeGetCardData()
        genreResponseCall!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setListData(response.body().toString());
                    } else {
                        main_progress_bar.visibility = View.GONE
                        no_data_text.visibility = View.VISIBLE
                    }
                } else {
                    main_progress_bar.visibility = View.GONE
                    no_data_text.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                main_progress_bar.visibility = View.GONE
                no_data_text.visibility = View.VISIBLE
            }
        })
    }


    // This method is used for parsing the JSON data and converting to CardResponse class. It also remove the unwanted values from the String Json.

    private fun setListData(response: String) {
        var resString: String? = response

        resString = resString?.replace("/", "")
        resString = resString?.replace("\\<[^>]*>", "")

        var cardResponse: CardResponse =
            Gson().fromJson(resString, CardResponse::class.java)

        cardList = cardResponse.data!!
        setUpView()
    }
}
