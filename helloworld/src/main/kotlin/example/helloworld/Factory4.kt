package brex.helloworld

import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton


class Fact4{}

@Factory
class Factory4 {

    @Singleton
    fun fact4(): Fact4 {
        return Fact4()
    }
}