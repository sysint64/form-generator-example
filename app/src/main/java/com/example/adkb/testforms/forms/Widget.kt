package com.example.adkb.testforms.forms

import android.view.LayoutInflater
import android.view.ViewGroup

enum class Size(val size: Int) {
    WRAP_CONTENT(ViewGroup.LayoutParams.WRAP_CONTENT),
    MATCH_PARENT(ViewGroup.LayoutParams.MATCH_PARENT),
}

interface Widget {
    fun inflate(layoutInflater: LayoutInflater, container: ViewGroup)
    fun setForm(form: Form)
}
