package com.example.adkb.testforms.complex_document

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.adkb.testforms.R
import com.example.adkb.testforms.forms.Form
import com.example.adkb.testforms.forms.converters.json.JsonOutputConverter
import com.example.adkb.testforms.forms.*
import com.example.adkb.testforms.forms.fields.*
import com.example.adkb.testforms.forms.validators.MaxMinStringValidator
import com.example.adkb.testforms.forms.validators.RequiredValidator
import kotlinx.android.synthetic.main.my_form.*

class ComplexFormActivity : AppCompatActivity(), View, OnClickListener {
    private lateinit var myForm: Form;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_form)

        myForm = form(formContainer) {
            editText(name = "key 1", validators = listOf(RequiredValidator(), MaxMinStringValidator(min = 3, max = 5)))
            editText(name = "key 2", validators = listOf(RequiredValidator())) {
                hint = "Hello my friend!"
                textInputLayoutAttrs {
                    (layoutParams as ViewGroup.MarginLayoutParams).setMargins(10, 10, 10, 10)
                }
            }

            val radioGroupRequiredValidator = RequiredValidator()
                    .addHook(::radioButtonRequiredValidatorHook)
                    .addErrorTextHook { Pair(true, "This field is required for radio group") }

            radioButtonsGroup(name = "key 3", validators = listOf(radioGroupRequiredValidator)) {
                for (i in 1..3)
                    radioButton(name = "radio " + i.toString(), text = "Item " + i.toString())

                radioButton(name = "radio 4", text = "Item 4", value = 12)
            }

            checkbox(name ="rules", text = "I'm accept the terms", validators = listOf(RequiredValidator())) {
                exclude = true
            }

            checkbox(name ="test", text = "Test")
        }

        submitButton.setOnClickListener(this)
    }

    override fun onClick(p0: android.view.View?) {
        if (myForm.isValid()) {
            AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage(JsonOutputConverter(myForm).convert())
                    .create()
                    .show()
        }
    }
}
