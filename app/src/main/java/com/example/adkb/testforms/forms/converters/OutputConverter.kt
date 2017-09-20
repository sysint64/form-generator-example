package com.example.adkb.testforms.forms.converters

interface OutputConverter<out T> {
  fun convert() : T
}