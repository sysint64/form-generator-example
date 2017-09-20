package com.example.adkb.testforms.forms.converters.json

import com.example.adkb.testforms.forms.Form
import com.example.adkb.testforms.forms.converters.OutputConverter

class JsonOutputConverter(private val form: Form) : OutputConverter<String> {
    override fun convert(): String {
        var json = "{"

        for (field in form.fields) {
            if (field.exclude)
                continue

            json += "\"" + field.name + "\": " + resolveTypeAdapter(field.getValue()).toJson()
            json += ","
        }

        json = json.substring(0, json.length - 1)  // rm last comma
        json += "}"
        return json
    }
}
