package example.helloworld

import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton


class Fact3 {}

@Factory
class Factory3 {

    @Singleton
    fun fact3(): Fact3 {
        return Fact3()
    }
}