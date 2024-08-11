package brex.helloworld

import java.time.LocalTime
import io.micronaut.core.annotation.Introspected
import jakarta.validation.constraints.NotBlank


@Introspected
data class Hello1(
    @NotBlank val greeting: String,
    val time: LocalTime
)