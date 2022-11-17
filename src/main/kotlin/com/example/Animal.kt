package com.example

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import io.micronaut.serde.annotation.Serdeable

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "animalType",
        defaultImpl = Cat::class
)
@JsonSubTypes(JsonSubTypes.Type(value = Dog::class, name = "dog"))
@Serdeable
abstract class Animal(
    open val field1: String,
    open val field2: Any,
)

@JsonTypeName("dog")
@Serdeable
data class Dog (
    override val field1: String,
    override val field2: String
): Animal(field1, field2)

@Serdeable
data class Cat (
        override val field1: String,
        override val field2: Number
): Animal(field1, field2)


