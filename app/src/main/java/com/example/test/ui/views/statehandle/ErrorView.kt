package com.example.test.ui.views.statehandle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.data.models.response.ErrorCode

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorCode: Int,
    onRefreshClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text =
            if (errorCode == ErrorCode.BAD_RESPONSE_CODE) stringResource(R.string.something_went_wrong_label)
            else stringResource(R.string.check_internet_connection_label)
        )
        RefreshButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize()
                .fillMaxWidth(0.6f),
            onClick = onRefreshClick
        )
    }
}

@Preview(
    showBackground = true,
    heightDp = 400,
    widthDp = 400
)
@Composable
fun ErrorViewPreview() {
    ErrorView(modifier = Modifier.fillMaxSize(), errorCode = ErrorCode.BAD_RESPONSE_CODE) {

    }
}