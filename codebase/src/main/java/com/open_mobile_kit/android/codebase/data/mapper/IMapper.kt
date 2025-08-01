package com.open_mobile_kit.android.codebase.data.mapper

/**
 * A generic interface for mapping between two types.
 *
 * This is typically used in the data layer to map Data Transfer Objects (DTOs)
 * from the network or local database to Domain Entities, and vice-versa.
 *
 * @param I The input type.
 * @param O The output type.
 */
interface IMapper<in I, out O> {
    /**
     * Maps an object from type [I] to type [O].
     * @param input The object to map.
     * @return The mapped object.
     */
    fun map(input: I): O
}
