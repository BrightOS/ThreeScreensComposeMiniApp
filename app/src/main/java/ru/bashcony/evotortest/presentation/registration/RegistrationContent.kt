package ru.bashcony.evotortest.presentation.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import ru.bashcony.evotortest.presentation.common.EvotorPrimaryButton
import ru.bashcony.evotortest.presentation.common.EvotorScaffold
import ru.bashcony.evotortest.presentation.common.EvotorTextField
import ru.bashcony.evotortest.presentation.common.EvotorTitle
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun RegistrationContent(
    onNavigationUp: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val state: RegistrationState by viewModel.viewState.collectAsStateWithLifecycle()

    EvotorScaffold(
        onNavigationUp = onNavigationUp,
    ) {
        val registrationState = state
        when (registrationState) {
            RegistrationState.Initial -> {
                RegistrationContent(
                    padding = it,
                    updateCurrentUser = viewModel::updateCurrentUser,
                )
            }

            RegistrationState.ChangesSaved -> {
                onNavigationUp()
            }

            RegistrationState.Error -> Unit
        }
    }
}

@Composable
private fun RegistrationContent(
    padding: PaddingValues,
    updateCurrentUser: (UserEntity) -> Unit,
) {
    var membershipNumber by remember { mutableStateOf("") }
    var bankCode by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    var membershipNumberError by remember { mutableStateOf(true) }
    var bankCodeError by remember { mutableStateOf(true) }
    var firstNameError by remember { mutableStateOf(true) }
    var lastNameError by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 18.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .weight(1f),
        ) {
            EvotorTitle(text = "Регистрация для клиентов банка")
            MembershipTextField(
                value = membershipNumber,
                isError = { membershipNumberError = it },
                onValueChange = { membershipNumber = it },
            )
            BankCodeTextField(
                value = bankCode,
                isError = { bankCodeError = it },
                onValueChange = { bankCode = it },
            )
            FirstNameTextField(
                value = firstName,
                isError = { firstNameError = it },
                onValueChange = { firstName = it },
            )
            LastNameTextField(
                value = lastName,
                isError = { lastNameError = it },
                onValueChange = { lastName = it },
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = AnnotatedString(
                text = "Нажимая на кнопку продолжить, вы соглашаетесь ",
            ).plus(
                AnnotatedString(
                    text = "с условиями участия",
                    spanStyle = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                    ),
                )
            ),
            color = EvotorColor.DisabledPrimaryContent,
            lineHeight = 14.sp,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        EvotorPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Продолжить",
            enabled = !(membershipNumberError || bankCodeError || firstNameError || lastNameError),
            onClick = {
                if (!membershipNumberError) updateCurrentUser(
                    UserEntity(
                        memberNumber = membershipNumber,
                        bankCode = bankCode,
                        phoneNumber = "+79377808307",
                        firstName = firstName,
                        lastName = lastName,
                    )
                )
            },
        )
    }
}

@Composable
private fun MembershipTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
) {
    EvotorTextField(
        modifier = Modifier.padding(top = 20.dp),
        value = value,
        placeholderText = "Номер участника",
        supportingText = "Номер из 16 цифр, который вы получили от банка",
        errorText = "Некорректные данные",
        onValueChange = onValueChange,
        inputType = EvotorTextField.InputType.Number,
        visualTransformation = EvotorTextField.Transformation.MembershipNumber,
        maxLength = 16,
        isTextValid = { text ->
            RegistrationValidator.isNumberValid(text).also { isError(!it) }
        },
    )
}

@Composable
private fun BankCodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
) {
    EvotorTextField(
        modifier = Modifier.padding(top = 10.dp),
        value = value,
        placeholderText = "Код",
        supportingText = "Код, который вы получили от банка",
        errorText = "Некорректные данные",
        onValueChange = onValueChange,
        inputType = EvotorTextField.InputType.Number,
        maxLength = 6,
        isTextValid = { text ->
            RegistrationValidator.isOtherFieldsValid(text).also { isError(!it) }
        },
    )
}

@Composable
private fun FirstNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
) {
    EvotorTextField(
        modifier = Modifier.padding(top = 10.dp),
        value = value,
        placeholderText = "Имя",
        supportingText = "Имя (на латинице, как в загранпаспорте)",
        errorText = "Некорректные данные",
        onValueChange = onValueChange,
        inputType = EvotorTextField.InputType.Text,
        isTextValid = { text ->
            RegistrationValidator.isOtherFieldsValid(text).also { isError(!it) }
        },
    )
}

@Composable
private fun LastNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
) {
    EvotorTextField(
        modifier = Modifier.padding(top = 10.dp),
        value = value,
        placeholderText = "Фамилия",
        supportingText = "Фамилия (на латинице, как в загранпаспорте)",
        errorText = "Некорректные данные",
        onValueChange = onValueChange,
        inputType = EvotorTextField.InputType.Text,
        isTextValid = { text ->
            RegistrationValidator.isOtherFieldsValid(text).also { isError(!it) }
        },
        imeAction = ImeAction.Done,
    )
}

@Preview
@Composable
private fun RegistrationContentPreview() {
    EvotorTestTheme {
        EvotorScaffold(
            onNavigationUp = {},
        ) {
            RegistrationContent(
                padding = it,
                updateCurrentUser = {},
            )
        }
    }
}