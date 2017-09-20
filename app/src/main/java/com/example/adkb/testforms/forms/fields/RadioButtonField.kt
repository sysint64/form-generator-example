package com.example.adkb.testforms.forms.fields

import android.view.ViewGroup
import android.widget.RadioButton
import org.jetbrains.anko.radioButton

open class RadioButtonField(name: String, val text: String, val itemValue: Any = text) : Field(name) {
    lateinit var radioButton: RadioButton
        private set

    override fun getValue(): Any {
        return itemValue
    }

    override fun inflate(parent: ViewGroup) {
        val field = this@RadioButtonField
        this.radioButton = parent.radioButton {
            text = field.text;
        }
    }

    override fun setError(error: String) {
        radioButton.error = error
    }

    override fun clearError() {
        radioButton.error = null
    }

    fun attrs(init: (RadioButton).() -> Unit) {
        radioButton.init()
    }
}

// DSL
fun RadioButtonsGroup.radioButton(name: String, text: String, value: Any = text): RadioButtonField {
    val field = RadioButtonField(name, text, value)
    addRadioButton(field)
    field.inflate(this.radioGroup)
    return field
}

fun RadioButtonsGroup.radioButton(name: String, text: String, value: Any = text, init: (RadioButtonField).() -> Unit): RadioButtonField {
    val field = RadioButtonField(name, text, value)
    addRadioButton(field)
    field.inflate(this.radioGroup)
    field.init()
    return field
}
