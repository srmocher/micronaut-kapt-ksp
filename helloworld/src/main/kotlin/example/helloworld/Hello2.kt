package example.helloworld

import java.time.LocalTime
import io.micronaut.core.annotation.Introspected
import jakarta.validation.constraints.NotBlank


@Introspected
data class Hello2(
    @NotBlank val greeting: String,
    val time: LocalTime
)