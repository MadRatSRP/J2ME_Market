package com.madrat.j2me_market.model

import android.text.Spannable
import android.text.SpannableString

data class SpannableSite(
    val title: Spannable
) {
    constructor(title: String)
            : this(SpannableString(title))
}