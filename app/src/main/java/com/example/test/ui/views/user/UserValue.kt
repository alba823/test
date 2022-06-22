package com.example.test.ui.views.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.ui.theme.Paddings
import com.example.test.ui.theme.Typography

@Composable
fun <T> UserValue(name: String, value: T) {
    Text(
        modifier = Modifier.padding(Paddings.small),
        text = name.plus(stringResource(R.string.dots_divider)).plus(value),
        style = Typography.body1
    )
}

@Preview(showBackground = true)
@Composable
fun UserValuePreviewString() {
    UserValue(name = stringResource(id = R.string.country_label), value = "Japan")
}

@Preview(showBackground = true)
@Composable
fun UserValuePreviewInt() {
    UserValue(name = stringResource(id = R.string.age_label), value = 18)
}