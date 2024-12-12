package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorTextField(
    value: String,
    placeholderText: String,
    supportingText: String,
    errorText: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = -1,
    inputType: EvotorTextField.InputType = EvotorTextField.InputType.Text,
    visualTransformation: EvotorTextField.Transformation = EvotorTextField.Transformation.None,
    isTextValid: (String) -> Boolean = { true },
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    var error by remember { mutableStateOf(false) }
    var everHadFocus by remember { mutableStateOf(false) }
    var currentSupportingText by remember { mutableStateOf(supportingText) }

    val onFocusChanged = { state: FocusState ->
        if (state.hasFocus) everHadFocus = true
        error = !state.hasFocus && !isTextValid(value) && everHadFocus
        currentSupportingText = if (error) errorText else supportingText
    }

    val onValueChange = { newValue: String ->
        if (error) error = false

        val croppedValue = if (maxLength > -1)
            newValue.take(maxLength)
        else
            newValue

        isTextValid(croppedValue)
        onValueChange(croppedValue)
    }

    OutlinedTextField(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .onFocusChanged(onFocusChanged),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = when (inputType) {
                EvotorTextField.InputType.Number -> KeyboardType.NumberPassword
                EvotorTextField.InputType.Text -> KeyboardType.Text
            },
            imeAction = imeAction,
        ),
        shape = RoundedCornerShape(16.dp),
        colors = EvotorTextField.colors(),
        value = value,
        onValueChange = onValueChange,
        isError = error,
        placeholder = {
            Text(text = placeholderText)
        },
        supportingText = {
            Text(
                text = currentSupportingText,
                fontSize = 10.sp,
            )
        },
    )
}

object EvotorTextField {
    @Stable
    sealed class InputType {
        data object Number : InputType()
        data object Text : InputType()
    }

    @Stable
    sealed class Transformation : VisualTransformation {

        data object MembershipNumber : Transformation() {
            private val offsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset < 4 -> offset
                        offset < 8 -> offset + 1
                        offset < 12 -> offset + 2
                        else -> offset + 3
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 4 -> offset
                        offset <= 9 -> offset - 1
                        offset <= 14 -> offset - 2
                        else -> offset - 3
                    }
                }
            }

            override fun filter(text: AnnotatedString): TransformedText {
                val transformedText = buildString {
                    text.forEachIndexed { index, char ->
                        append(char)
                        if ((index + 1) % 4 == 0 && index < 15) append(" ")
                    }
                }

                return TransformedText(AnnotatedString(transformedText), offsetTranslator)
            }
        }

        data object None : Transformation() {
            override fun filter(text: AnnotatedString): TransformedText {
                return TransformedText(text, OffsetMapping.Identity)
            }
        }
    }

    @Composable
    fun colors() = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = EvotorColor.Container,
        errorContainerColor = EvotorColor.Container,
        disabledContainerColor = EvotorColor.Container,
        unfocusedContainerColor = EvotorColor.Container,
        focusedBorderColor = EvotorColor.Container,
        unfocusedBorderColor = EvotorColor.Container,
        errorBorderColor = EvotorColor.Error,
        errorTextColor = EvotorColor.Error,
        errorPlaceholderColor = EvotorColor.Error,
        errorSupportingTextColor = EvotorColor.Error,
        unfocusedTextColor = EvotorColor.PrimaryContent,
        focusedTextColor = EvotorColor.PrimaryContent,
        focusedPlaceholderColor = EvotorColor.SecondaryContent,
        unfocusedPlaceholderColor = EvotorColor.SecondaryContent,
        focusedSupportingTextColor = EvotorColor.SecondaryContent,
        unfocusedSupportingTextColor = EvotorColor.SecondaryContent,
        disabledSupportingTextColor = EvotorColor.SecondaryContent,
    )
}

@Preview
@Composable
fun EvotorTextFieldPreview() {
    EvotorTestTheme {
        Column {
            EvotorTextField(
                value = "",
                placeholderText = "Номер участника",
                supportingText = "Номер из 16 цифр, который вы получили от банка",
                errorText = "Некорректные данные",
                onValueChange = {},
                isTextValid = { true },
            )
            EvotorTextField(
                value = "1234567812344522",
                placeholderText = "Номер участника",
                supportingText = "Номер из 16 цифр, который вы получили от банка",
                errorText = "Некорректные данные",
                visualTransformation = EvotorTextField.Transformation.MembershipNumber,
                onValueChange = {},
                isTextValid = { true },
            )
            EvotorTextField(
                value = "12345678123445",
                placeholderText = "Номер участника",
                supportingText = "Номер из 16 цифр, который вы получили от банка",
                errorText = "Некорректные данные",
                visualTransformation = EvotorTextField.Transformation.MembershipNumber,
                onValueChange = {},
                isTextValid = { false },
            )
        }
    }
}