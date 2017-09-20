package com.example.adkb.testforms.forms.fields

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.adkb.testforms.R
import com.example.adkb.testforms.forms.Form
import com.example.adkb.testforms.forms.validators.Validator
import org.jetbrains.anko.checkBox
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView

open class CheckBoxField(name: String, val text: String) : Field(name) {
    lateinit var checkbox: CheckBox
    lateinit var errorTextView: TextView private set

    override fun getValue(): Any {
        return checkbox.isChecked
    }

    override fun inflate(parent: ViewGroup) {
        val field = this@CheckBoxField

        errorTextView = parent.textView {
            val color = ContextCompat.getColor(form.context, R.color.formErrorColor)
            textColor = color
            visibility = View.GONE
        }

        checkbox = parent.checkBox {
            text = field.text
        }
    }

    override fun setError(error: String) {
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error
    }

    override fun clearError() {
        errorTextView.visibility = View.GONE
    }

    fun attrs(init: (CheckBox).() -> Unit) {
        checkbox.init()
    }
}

// DSL

fun Form.checkbox(name: String, text: String, validators: List<Validator> = listOf()): CheckBoxField {
    val field = CheckBoxField(name, text)
    addField(field)
    field.validators = validators
    field.inflate(this.container)
    return field
}

fun Form.checkbox(name: String, text: String, validators: List<Validator> = listOf(), init: (CheckBoxField).() -> Unit): CheckBoxField {
    val field = checkbox(name, text, validators)
    field.init()
    return field
}
