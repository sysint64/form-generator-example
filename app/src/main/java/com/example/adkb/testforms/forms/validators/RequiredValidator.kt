package com.example.adkb.testforms.forms.validators

import com.example.adkb.testforms.R

class RequiredValidator : Validator() {
    override fun getErrorText(value: Any?): String {
        return errorFromRes(R.string.required_error)
    }

    override fun isValid(value: Any?): Boolean {
        if (value == null)
            return false

        if (value is String)
            return !value.isBlank()

        if (value is Boolean)
            return value

        throw UnsupportedOperationException()
    }
}
