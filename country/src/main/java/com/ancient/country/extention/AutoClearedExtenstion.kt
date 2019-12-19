package com.ancient.country.extention

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A lazy property that gets cleaned up when the activity is destroyed.
 *
 * Accessing this variable in a destroyed activity will throw NPE.
 */
class AutoClearedFragmentValue<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                _value = null
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
                "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

/**
 * Creates an [AutoClearedFragmentValue] associated with this activity.
 */
fun <T : Any> Fragment.autoCleared() = AutoClearedFragmentValue<T>(this)

/**
 * A lazy property that gets cleaned up when the activity is destroyed.
 *
 * Accessing this variable in a destroyed activity will throw NPE.
 */
class AutoClearedActivityValue<T : Any>(activity: AppCompatActivity) : ReadWriteProperty<AppCompatActivity, T> {
    private var _value: T? = null

    init {
        activity.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                _value = null
            }
        })
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
                "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: T) {
        _value = value
    }
}

/**
 * Creates an [AutoClearedFragmentValue] associated with this activity.
 */
fun <T : Any> AppCompatActivity.autoCleared() = AutoClearedActivityValue<T>(this)