Country Selection Library
===========================
Country's list with flag image. Can launch this activity to show the list of country with flag or without it.

How to add to your project
--------------

Add repository info in your root project gradle file

    // project.gradle
    repositories {
      jcenter()
    }

Add this below in your app.gradle

    // app.gradle
    dependencies {
      implementation 'com.ancient.country:country:1.0.4'
    }

Implementation
--------------

Simple steps to acheive. Call country search activity with result.

      startActivityForResult( Intent(context, CountrySearchActivity::class.java), RequestCode.COUNTRY_SEARCH_CODE)
      
You will receive your result once the user is selected the country
  
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


## License

[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Copyright (C) 2019 Naveen Kumar Kuppan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
