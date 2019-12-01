package com.dev.mahmoud_ashraf.marvelapp.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}