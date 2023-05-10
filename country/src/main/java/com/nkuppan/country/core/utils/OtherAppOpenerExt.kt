package com.nkuppan.country.core.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.nkuppan.country.R
import java.util.*

private const val TAG = "Country: Speech Request"

/**
 * Showing google speech input dialog
 */
fun Fragment.promptSpeechInputForCallback(resultReceiver: ActivityResultLauncher<Intent>) {

    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))
    try {
        resultReceiver.launch(intent)
    } catch (a: ActivityNotFoundException) {
        Log.d(TAG, a.localizedMessage ?: "")
    }
}