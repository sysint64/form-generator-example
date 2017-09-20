package com.example.adkb.testforms.forms.converters.json

import com.example.adkb.testforms.forms.fields.RadioButtonsGroup

fun resolveTypeAdapter(value: Any?): TypeAdapter {
    if (value is String) {
        return StringTypeAdapter(value)
    }

    if (value is Int || value is Long || value is Float || value is Double || value is Boolean) {
        return BasicTypeAdapter(value)
    }

    if (value is RadioButtonsGroup.RadioButtonsList) {
        return RadioButtonsGroupTypeAdapter(value)
    }

    throw UnsupportedOperationException()
}
