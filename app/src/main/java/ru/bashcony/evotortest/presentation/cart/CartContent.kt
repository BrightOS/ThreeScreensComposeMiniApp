package ru.bashcony.evotortest.presentation.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.bashcony.evotortest.domain.cart.entity.DayCartEntity
import ru.bashcony.evotortest.presentation.common.EvotorCard
import ru.bashcony.evotortest.presentation.common.EvotorScaffold
import ru.bashcony.evotortest.presentation.common.EvotorSubtitle
import ru.bashcony.evotortest.presentation.common.EvotorTitle
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun CartContent(
    onNavigationUp: () -> Unit,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val state: CartState by viewModel.viewState.collectAsStateWithLifecycle()

    EvotorScaffold(
        onNavigationUp = onNavigationUp,
    ) {
        val cartState = state
        when (cartState) {
            is CartState.CartLoaded -> {
                CartContent(cartState.cart, it)
            }

            CartState.Loading -> Unit
            CartState.Error -> Unit
        }
    }
}

@Composable
private fun CartContent(
    cart: List<DayCartEntity>,
    padding: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 18.dp)
            .fillMaxSize(),
    ) {
        item {
            EvotorTitle(text = "Мои покупки")
        }
        cart.forEach {
            item {
                EvotorSubtitle(text = it.date)
            }
            items(it.name) {
                EvotorCard(
                    startContent = EvotorCard.StartContent.Text(text = it),
                    chevronEnabled = false,
                )
            }
        }
    }
}


@Preview
@Composable
private fun CartContentPreview() {
    EvotorTestTheme {
        EvotorScaffold(
            onNavigationUp = {},
        ) {
            CartContent(
                padding = it,
                cart = listOf(
                    DayCartEntity(
                        date = "10.09.2022",
                        name = listOf("Услуга №1", "Услуга №2"),
                    )
                ),
            )
        }
    }
}