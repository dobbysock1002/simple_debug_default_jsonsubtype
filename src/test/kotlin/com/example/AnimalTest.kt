package com.example

import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class AnimalTest {

    @Test
    fun testExpectedSubType(objectMapper: ObjectMapper) {
        val expectedOutput = Dog("test", "test")
        val input = """
            {"field1":"test", "field2":"test", "animalType": "dog"}
        """.trimIndent()

        val animal = objectMapper.readValue(input, Animal::class.java)

        Assertions.assertEquals(expectedOutput, animal)
    }

    @Test
    fun testUnexpectedSubType(objectMapper: ObjectMapper) {
        val expectedOutput = Cat("test", 3)
        val input = """
            {"field1":"test", "field2":3, "animalType": "surprise"}
        """.trimIndent()

        val animal = objectMapper.readValue(input, Animal::class.java)

        Assertions.assertEquals(expectedOutput, animal)
    }
}