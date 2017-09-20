package com.example.adkb.testforms.forms.validators

import com.example.adkb.testforms.R

class MaxMinStringValidator(val min: Int, val max: Int): Validator() {
    override fun isValid(value: Any?): Boolean {
        if (value !is String)
            throw UnsupportedOperationException()

        return value.length in min..max
    }

    override fun getErrorText(value: Any?): String {
        if (value !is String)
            throw UnsupportedOperationException()

        if (value.length < min) {
            val format = context.resources.getString(R.string.min_error)
            return String.format(format, min)
        }

        if (value.length > max) {
            val format = context.resources.getString(R.string.max_error)
            return String.format(format, max)
        }

        throw UnsupportedOperationException()
    }
}
