package ru.bashcony.evotortest.domain.cart.usecase

import ru.bashcony.evotortest.domain.cart.CartRepository
import ru.bashcony.evotortest.domain.cart.entity.DayCartEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale
import javax.inject.Inject

private const val DATE_PATTERN = "dd.MM.yyyy"
private const val DATE_AND_TIME_DIVIDER = 'T'

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): List<DayCartEntity> {
        return cartRepository.getCart()
            .flatMap {
                buildList {
                    it.name.forEach { value ->
                        add(Pair(it.date, value))
                    }
                }
            }
            .groupBy { it.first.substringBefore(DATE_AND_TIME_DIVIDER) }
            .map { Pair(it.key.toMillis(), it.value.map { it.second }) }
            .sortedBy { it.first }
            .asReversed()
            .map {
                DayCartEntity(
                    date = it.first.toCorrectDateString(),
                    name = it.second,
                )
            }
    }

    companion object {
        private val outputDateFormat = SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH)

        private fun String.toMillis(): Long {
            return LocalDate.parse(this)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()
        }

        private fun Long.toCorrectDateString(): String {
            return outputDateFormat.format(Date(this))
        }
    }
}