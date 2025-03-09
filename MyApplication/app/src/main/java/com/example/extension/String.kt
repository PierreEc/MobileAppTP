package com.example.extension

import java.text.Normalizer

fun String.removeAccents(): String {
    val normalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalizedString.replace("\\p{M}".toRegex(), "")
}