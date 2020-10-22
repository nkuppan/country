Country Selection Library
===========================
[ ![Download](https://api.bintray.com/packages/naveenkumarn27/Country/country/images/download.svg) ](https://bintray.com/naveenkumarn27/Country/country/_latestVersion)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Build Status](https://travis-ci.org/naveenkumarn27/country.svg?branch=master)](https://travis-ci.org/naveenkumarn27/country)

Country's list with flag image. Can launch this as an activity to show the list of country with flag or as a dialog.

How to add to your project
--------------

Sample implementation gif for country selection and using search functionality 

<img src="screenshots/country.gif" width="270" height="480"/>

Add repository info in your root project gradle file

```gradle
// project.gradle
repositories {
    jcenter()
}
```

Add this below in your app.gradle

```gradle
// app.gradle
dependencies {
    implementation 'com.ancient.country:country:1.0.7'
}
```

Implementation
--------------

Simple steps to achieve. Call country search activity with result.

Starting country selection as activity based

```kotlin
startActivityForResult( Intent(context, CountrySearchActivity::class.java), RequestCode.COUNTRY_SEARCH_CODE)
```

You will receive your result once the user is selected the country

```kotlin
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
```

Starting country selection as a dialog
```kotlin
val ft = supportFragmentManager.beginTransaction()
val countryListDialogFragment = CountryListDialogFragment()
countryListDialogFragment.countrySelection = {
    changeValues(it)
    countryListDialogFragment.dismiss()
}
countryListDialogFragment.show(ft, "dialog")
```

Starting country selection as a bottom sheet dialog
```kotlin
val ft = supportFragmentManager.beginTransaction()
val countryListBottomSheet = CountryListBottomSheet()
countryListBottomSheet.countrySelection = {
    changeValues(it)
    countryListBottomSheet.dismiss()
}
countryListBottomSheet.show(ft, "bottom_sheet_dialog")
```

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
