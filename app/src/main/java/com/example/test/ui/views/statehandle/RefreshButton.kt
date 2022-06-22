package com.example.test.ui.views.statehandle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.ui.theme.Paddings
import com.example.test.ui.theme.Shapes
import com.example.test.ui.theme.Typography

@Composable
fun RefreshButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.padding(Paddings.regular),
        shape = Shapes.large,
        onClick = onClick
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.try_again_button_label),
                style = Typography.button
            )
            Icon(
                modifier = Modifier.padding(start = Paddings.small),
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = stringResource(R.string.try_again_button_description)
            )
        }
    }
}

@Preview
@Composable
fun RefreshButtonPreview() {
    RefreshButton {

    }
}