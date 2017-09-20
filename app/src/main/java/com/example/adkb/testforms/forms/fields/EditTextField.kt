package com.example.adkb.testforms.forms.fields

import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import com.example.adkb.testforms.forms.Form
import com.example.adkb.testforms.forms.validators.Validator
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputLayout

open class EditTextField(name: String) : Field(name) {
    private lateinit var editText: EditText
    private lateinit  var textInputLayout: TextInputLayout

    var hint: String = ""
        set(value) {
            textInputLayout.hint = value
            field = value
        }

    override fun getValue(): String {
        Log.d("EditTextField", editText.text.toString())
        return editText.text.toString()
    }

    override fun inflate(parent: ViewGroup) {
        val field = this@EditTextField

        this.textInputLayout = parent.textInputLayout {
            field.editText = editText {
                hint = field.hint
            }
        }
    }

    override fun setError(error: String) {
        editText.error = error
    }

    override fun clearError() {
        editText.error = null
    }

    fun editTextAttrs(init: (EditText).() -> Unit) {
        editText.init()
    }

    fun textInputLayoutAttrs(init: (TextInputLayout).() -> Unit) {
        textInputLayout.init()
    }
}

// DSL
fun Form.editText(name: String): EditTextField {
    val field = EditTextField(name)
    addField(field)
    field.inflate(this.container)
    return field
}

fun Form.editText(name: String, validators: List<Validator>): EditTextField {
    val field = editText(name)
    field.validators = validators
    return field
}

fun Form.editText(name: String, validators: List<Validator>, init: (EditTextField).() -> Unit): EditTextField {
    val field = editText(name, validators)
    field.init()
    return field
}

fun Form.editText(name: String, init: (EditTextField).() -> Unit): EditTextField {
    val field = editText(name)
    field.init()
    return field
}
