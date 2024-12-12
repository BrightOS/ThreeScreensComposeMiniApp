package ru.bashcony.evotortest.data.common.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val SharedPreferences.delegates get() = SharedPreferenceDelegates(this)

class SharedPreferenceDelegates(private val prefs: SharedPreferences) {
    fun boolean(
        default: Boolean = false,
        key: String? = null,
        onSet: (Boolean) -> Unit = {}
    ): ReadWriteProperty<Any, Boolean> =
        create(default, key, prefs::getBoolean, { k, d ->
            onSet(d)
            prefs.edit().putBoolean(k, d)
        })

    fun int(
        default: Int = 0,
        key: String? = null,
        onSet: (Int) -> Unit = {}
    ): ReadWriteProperty<Any, Int> =
        create(default, key, prefs::getInt, { k, d ->
            onSet(d)
            prefs.edit().putInt(k, d)
        })

    fun float(
        default: Float = 0f,
        key: String? = null,
        onSet: (Float) -> Unit = {}
    ): ReadWriteProperty<Any, Float> =
        create(default, key, prefs::getFloat, { k, d ->
            onSet(d)
            prefs.edit().putFloat(k, d)
        })

    fun long(
        default: Long = 0L,
        key: String? = null,
        onSet: (Long) -> Unit = {}
    ): ReadWriteProperty<Any, Long> =
        create(default, key, prefs::getLong, { k, d ->
            onSet(d)
            prefs.edit().putLong(k, d)
        })

    fun string(
        default: String = "",
        key: String? = null,
        onSet: (String) -> Unit = {}
    ): ReadWriteProperty<Any, String> =
        create(default, key, { k, d -> prefs.getString(k, d) as String }, { k, d ->
            onSet(d)
            prefs.edit().putString(k, d)
        })

    private fun <T> create(
        default: T,
        key: String? = null,
        getter: (key: String, default: T) -> T,
        setter: (key: String, value: T) -> SharedPreferences.Editor,
    ) = object : ReadWriteProperty<Any, T> {
        private fun key(property: KProperty<*>) = key ?: property.name

        override fun getValue(thisRef: Any, property: KProperty<*>): T =
            getter(key(property), default)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            setter(key(property), value).apply()
    }
}