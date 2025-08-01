package com.open_mobile_kit.android.codebase.domain.entity

/**
 * Base class for Value Objects.
 *
 * Value Objects are objects defined by their attributes, not a unique ID.
 * They should be immutable. It is recommended to implement them as data classes.
 *
 * Example:
 * data class Address(
 *     val street: String,
 *     val city: String
 * ) : ValueObject()
 */
abstract class ValueObject
