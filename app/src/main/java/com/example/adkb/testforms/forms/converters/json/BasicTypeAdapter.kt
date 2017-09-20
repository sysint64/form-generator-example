package com.example.adkb.testforms.forms.converters.json

open class BasicTypeAdapter(open val value: Any?): TypeAdapter {
    override fun toJson(): String {
        return value.toString()
    }
}
