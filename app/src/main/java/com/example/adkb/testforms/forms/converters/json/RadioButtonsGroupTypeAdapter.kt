package com.example.adkb.testforms.forms.converters.json

import com.example.adkb.testforms.forms.fields.RadioButtonsGroup

class RadioButtonsGroupTypeAdapter(val value: RadioButtonsGroup.RadioButtonsList): TypeAdapter {
    override fun toJson(): String {
        for (item in value) {
            if (item.radioButton.isChecked)
                return resolveTypeAdapter(item.getValue()).toJson()
        }

        return "null"
    }
}
