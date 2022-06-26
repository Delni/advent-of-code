package com.github.delni.advent_of_code.submarine

import java.util.stream.Collectors


data class Store(
    val label: String
)

data class DayWeight(
    val store: Store
)

class StoreParam {
    var trg2Ids: List<OfferStoreId> = trg2Offer
        .stream()
        .map { OfferStoreId(
            it.storeTrg,
            it.sku
        ) }
        .collect(Collectors::toList)
}