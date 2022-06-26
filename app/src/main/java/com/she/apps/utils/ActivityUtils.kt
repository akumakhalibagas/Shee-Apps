package com.she.apps.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.she.apps.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gabor Varadi on Medium (23-02-2020)
 * @see <a href="https://zhuinden.medium.com/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c">Simple one-liner ViewBinding </a>
 *
 * */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

/**
 * Created by Phillip Lackner (09-12-2021)
 * @see <a href="https://github.com/philipplackner/KotlinFlowsGuide/blob/cb1a28bb79701dd52b7cbdd553bfa50fce6c9a16/app/src/main/java/com/plcoding/kotlinflows/MainActivity.kt">KotlinFlowsGuide</a>
 *
 * */
fun <T> ComponentActivity.collectLifecycleFlow(flow: Flow<T>, collect: FlowCollector<T>) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}

fun dateTime(): String {
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
    return formatter.format(Date())
}

fun changeDateFormat(fullTime: String, format: String): String {
    val locale = Locale.getDefault()
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
    val formatter = SimpleDateFormat(format, locale)
    return parser.parse(fullTime)?.let { formatter.format(it) }.toString()
}

fun View.showDatePicker(callback: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->
            calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0)
            callback(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    datePickerDialog.setOnShowListener {
        hideKeyboard()
    }

    datePickerDialog.show()
}

fun View.hideKeyboard() {
    val inputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

class TimePickerHelper(
    context: Context,
    is24HourView: Boolean,
    isSpinnerType: Boolean = false
) {
    private var dialog: TimePickerDialog
    private var callback: Callback? = null
    private val listener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
        callback?.onTimeSelected(hourOfDay, minute)
    }

    init {
        val style = if (isSpinnerType) R.style.SpinnerTimePickerDialog else 0
        val cal = Calendar.getInstance()
        dialog = TimePickerDialog(
            context, style, listener,
            cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), is24HourView
        )
    }

    fun showDialog(hourOfDay: Int, minute: Int, callback: Callback?) {
        this.callback = callback
        dialog.updateTime(hourOfDay, minute)
        dialog.show()
    }

    interface Callback {
        fun onTimeSelected(hourOfDay: Int, minute: Int)
    }
}