package com.ancient.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ancient.country.model.CountryModel
import com.ancient.country.utils.RequestCode
import com.ancient.country.utils.RequestParam
import com.ancient.country.view.activity.CountrySearchActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        button = findViewById(R.id.search_activity)

        button.setOnClickListener {
            startActivityForResult(
                    Intent(this@MainActivity, CountrySearchActivity::class.java),
                    RequestCode.COUNTRY_SEARCH_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCode.COUNTRY_SEARCH_CODE && resultCode == Activity.RESULT_OK) {

            data?.apply {

                val country: CountryModel? = getParcelableExtra(RequestParam.SELECTED_VALUE)

                if (country != null) {
                    Snackbar.make(
                            button,
                            "Selected Country [ name, code ] [${country.countryName} , ${country.countryCode}]",
                            Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}