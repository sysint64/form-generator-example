package com.example.adkb.testforms.forms.converters.json

class StringTypeAdapter(val value: String): TypeAdapter {
    override fun toJson(): String {
        return "\"$value\""
    }
}
