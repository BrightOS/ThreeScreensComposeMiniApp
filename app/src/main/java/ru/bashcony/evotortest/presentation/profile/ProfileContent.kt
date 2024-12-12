package ru.bashcony.evotortest.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.bashcony.evotortest.R
import ru.bashcony.evotortest.Screen
import ru.bashcony.evotortest.domain.users.entity.UserEntity
import ru.bashcony.evotortest.presentation.common.EvotorCard
import ru.bashcony.evotortest.presentation.common.EvotorScaffold
import ru.bashcony.evotortest.presentation.common.EvotorSubtitle
import ru.bashcony.evotortest.presentation.common.EvotorSwitch
import ru.bashcony.evotortest.presentation.common.EvotorTitle
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorIcons
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun ProfileContent(
    onNavigationUp: () -> Unit,
    navigate: (Screen) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state: ProfileState by viewModel.viewState.collectAsStateWithLifecycle()

    EvotorScaffold(
        onNavigationUp = onNavigationUp,
    ) {
        val profileState = state
        when (profileState) {
            ProfileState.Error -> Unit
            ProfileState.Loading -> Unit
            is ProfileState.ShowToast -> {

            }
            is ProfileState.ProfileLoaded -> {
                ProfileContent(profileState.user, navigate, it)
            }
        }
    }
}

@Composable
private fun ProfileContent(
    userEntity: UserEntity,
    navigate: (Screen) -> Unit,
    padding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 18.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        EvotorTitle(
            text = "${userEntity.firstName}\n${userEntity.lastName}",
            subtitle = userEntity.phoneNumber,
            endIcon = EvotorIcons.Edit,
        )
        EvotorSubtitle(text = "Мои покупки")
        CartCard(navigate = navigate)
        EvotorSubtitle(text = "Настройки")
        EMailCard()
        BiometryCard()
        EvotorCard(
            startContent = EvotorCard.StartContent.Text(text = "Сменить 4-х значный код"),
        )
        EvotorCard(
            startContent = EvotorCard.StartContent.Text(text = "Регистрация для клиентов банка"),
            onClick = { navigate(Screen.Registration) }
        )
        LanguageCard()
    }
}

@Composable
private fun LanguageCard() {
    EvotorCard(
        startContent = EvotorCard.StartContent.Text(text = "Язык"),
        endContent = {
            Text(
                text = "русский",
                color = EvotorColor.PrimaryContent,
                fontSize = 12.sp,
            )
        }
    )
}

@Composable
private fun BiometryCard() {
    EvotorCard(
        startContent = EvotorCard.StartContent.Text(text = "Вход по биометрии"),
        endContent = {
            var checked: Boolean by remember { mutableStateOf(true) }
            // Согласно предоставленному дизайну, нужен был switch с меньшей высотой.
            // В принципе, его можно сделать полностью кастомно.
            // Нашёл в открытых источниках идеальный аналог - https://github.com/develNerd/JC-CustomSwitch,
            // Но, так как это стороннее решение, решил оставить всё как есть.
            // В продуктовой ситуации вынес бы на обсуждение с коллегами/руководителем.
            EvotorSwitch(
                checked = checked,
                onCheckedChange = { checked = it },
            )
        },
        chevronEnabled = false,
    )
}

@Composable
private fun EMailCard() {
    EvotorCard(
        startContent = EvotorCard.StartContent.Text("E-mail"),
        endContent = {
            Column {
                Text(
                    text = "kursantik341@gmail.com",
                    color = EvotorColor.PrimaryContent,
                    fontSize = 14.sp
                )
                // По ТЗ здесь должен был быть отступ поменьше, но т.к.
                Text(
                    text = "Необходимо подтвердить",
                    color = EvotorColor.Error,
                    fontSize = 12.sp
                )
            }
        },
    )
}

@Composable
private fun CartCard(
    navigate: (Screen) -> Unit,
) {
    EvotorCard(
        startContent = EvotorCard.StartContent.CustomComposable {
            Image(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Buy"
            )
        },
        onClick = { navigate(Screen.Cart) },
    )
}

@Preview
@Composable
private fun ProfileContentPreview() {
    EvotorTestTheme {
        EvotorScaffold(
            onNavigationUp = {},
        ) {
            ProfileContent(
                userEntity = UserEntity(
                    firstName = "Denis",
                    lastName = "Shaykhlbarin",
                    phoneNumber = "+79377808307",
                    memberNumber = "113123",
                    bankCode = "43234",
                ),
                navigate = {},
                padding = it,
            )
        }
    }
}