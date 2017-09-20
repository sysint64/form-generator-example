package com.example.adkb.testforms.forms

import android.content.Context
import android.view.ViewGroup
import com.example.adkb.testforms.forms.fields.Field

open class Form(val context: Context, val container: ViewGroup) {
    val fields: MutableList<Field> = ArrayList()

    fun addField(field: Field) {
        if (field in fields)
            return

        fields.add(field)
        field.form = this
    }

    fun inflate(parent: ViewGroup) {
        for (field in fields) {
            field.inflate(parent)
        }
    }

    fun isValid(): Boolean {
        var result = true

        for (field in fields) {
            val fieldIsValid = field.isValid()
            result = result && fieldIsValid
        }

        return result
    }
}

// DSL
fun Context.form(container: ViewGroup, init: (Form).() -> Unit): Form {
    val form = Form(this, container)
    form.init()
    return form
}
