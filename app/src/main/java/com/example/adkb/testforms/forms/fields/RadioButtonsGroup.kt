package com.example.adkb.testforms.forms.fields

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import com.example.adkb.testforms.R
import com.example.adkb.testforms.forms.Form
import com.example.adkb.testforms.forms.validators.Validator

import org.jetbrains.anko.*

open class RadioButtonsGroup(name: String) : Field(name) {
    lateinit var radioGroup: RadioGroup private set
    lateinit var errorTextView: TextView private set

    class RadioButtonsList : ArrayList<RadioButtonField>()
    val radioButtons: MutableList<RadioButtonField> = RadioButtonsList()

    override fun getValue(): Any {
        return radioButtons
    }

    override fun inflate(parent: ViewGroup) {
        this.errorTextView = parent.textView {
            val color = ContextCompat.getColor(form.context, R.color.formErrorColor)
            textColor = color
        }
        this.radioGroup = parent.radioGroup()
    }

    override fun setError(error: String) {
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error
    }

    override fun clearError() {
        errorTextView.visibility = View.GONE
    }

    fun addRadioButton(item: RadioButtonField) {
        radioButtons.add(item)
    }

    fun attrs(init: (RadioGroup).() -> Unit) {
        radioGroup.init()
    }
}

// DSL
fun Form.radioButtonsGroup(name: String, init: (RadioButtonsGroup).() -> Unit): RadioButtonsGroup {
    val field = RadioButtonsGroup(name)
    addField(field)
    field.inflate(this.container)
    field.init()
    return field
}

fun Form.radioButtonsGroup(name: String, validators: List<Validator>, init: (RadioButtonsGroup).() -> Unit): RadioButtonsGroup {
    val field = radioButtonsGroup(name, init)
    field.validators = validators
    return field
}

// Extend default validators
fun radioButtonRequiredValidatorHook(value: Any?): Pair<Boolean, Boolean> {
    var isValid = false
    var isHookType = false

    if (value is RadioButtonsGroup.RadioButtonsList) {
        isHookType = true
        isValid = !value.none { it.radioButton.isChecked }
    }

    return Pair(isHookType, isValid)
}
