package brex.helloworld

import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton


class Fact2 {}

@Factory
class Factory2 {

    @Singleton
    fun fact2(): Fact2 {
        return Fact2()
    }
}