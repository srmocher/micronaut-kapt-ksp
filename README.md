# Micronaut kapt vs ksp

Micronaut's annotation processing can be done with Kapt (Java based annotation processing) and KSP (native Kotlin symbol processing).
This simple example illustrates the performance improvements using KSP. Requires Micronaut 4 for KSP and JVM 17.

# Clean build

Run `bazel clean` (to have no incremental state/workers) and then

```shell
bazel build //helloworld/src/main/kotlin/brex/helloworld:hello_kapt
```
to view the timings for `kapt`

```shell
Task timings for //helloworld/src/main/kotlin/brex/helloworld:hello_kapt (total: 1964 ms):
|   * expand sources: 0 ms
|   * kapt (io.micronaut.annotation.processing.AggregatingTypeElementVisitorProcessor, io.micronaut.annotation.processing.BeanDefinitionInjectProcessor, io.micronaut.annotation.processing.TypeElementVisitorProcessor, io.micronaut.annotation.processing.PackageConfigurationInjectProcessor, io.micronaut.annotation.processing.ServiceDescriptionProcessor): 1923 ms
|   * compile classes: 31 ms
|     * kotlinc: 0 ms
|     * creating KAPT generated Java source jar: 8 ms
|     * creating KAPT generated Kotlin stubs jar: 8 ms
|     * creating KAPT generated stub class jar: 14 ms
```

takes close to 1.9 seconds with `kapt`.

Now, run `bazel clean` and then run

```shell
bazel build //helloworld/src/main/kotlin/brex/helloworld:hello_ksp 
```

to build with `ksp`, and the timings are as follows

```shell
INFO: From KotlinKsp //helloworld/src/main/kotlin/brex/helloworld:hello_ksp { kt: 6, java: 0, srcjars: 0 } for darwin_arm64:
info: [ksp] loaded provider(s): [io.micronaut.kotlin.processing.visitor.TypeElementSymbolProcessorProvider, io.micronaut.kotlin.processing.beans.BeanDefinitionProcessorProvider]
info: [ksp] Created 9 bean definitions
Task timings for //helloworld/src/main/kotlin/brex/helloworld:hello_ksp (total: 1347 ms):
|   * expand sources: 0 ms
|   * Ksp (io.micronaut.kotlin.processing.visitor.TypeElementSymbolProcessorProvider, io.micronaut.kotlin.processing.beans.BeanDefinitionProcessorProvider): 1324 ms
|   * compile classes: 14 ms
|     * kotlinc: 0 ms
|     * creating KSP generated src jar: 13 ms
```

which takes around 1.3 seconds, which is around **30% faster** with `ksp` compared to `kapt`.

# Incremental build

Making changes to one of the source files and then doing an incremental build provides another perspective.

After changing `Factory1.kt` and running `kapt`

```shell
Task timings for //helloworld/src/main/kotlin/brex/helloworld:hello_kapt (total: 503 ms):
|   * expand sources: 0 ms
|   * kapt (io.micronaut.annotation.processing.AggregatingTypeElementVisitorProcessor, io.micronaut.annotation.processing.BeanDefinitionInjectProcessor, io.micronaut.annotation.processing.TypeElementVisitorProcessor, io.micronaut.annotation.processing.PackageConfigurationInjectProcessor, io.micronaut.annotation.processing.ServiceDescriptionProcessor): 485 ms
|   * compile classes: 17 ms
|     * kotlinc: 0 ms
|     * creating KAPT generated Java source jar: 1 ms
|     * creating KAPT generated Kotlin stubs jar: 5 ms
|     * creating KAPT generated stub class jar: 11 ms
INFO: From KotlinCompile //helloworld/src/main/kotlin/brex/helloworld
```

It takes around 503 ms.

Similarly after changing `Factory1.kt`and running `ksp`

```shell
INFO: From KotlinKsp //helloworld/src/main/kotlin/brex/helloworld:hello_ksp { kt: 6, java: 0, srcjars: 0 } for darwin_arm64:
info: [ksp] loaded provider(s): [io.micronaut.kotlin.processing.visitor.TypeElementSymbolProcessorProvider, io.micronaut.kotlin.processing.beans.BeanDefinitionProcessorProvider]
info: [ksp] Created 9 bean definitions
Task timings for //helloworld/src/main/kotlin/brex/helloworld:hello_ksp (total: 367 ms):
|   * expand sources: 0 ms
|   * Ksp (io.micronaut.kotlin.processing.visitor.TypeElementSymbolProcessorProvider, io.micronaut.kotlin.processing.beans.BeanDefinitionProcessorProvider): 363 ms
|   * compile classes: 3 ms
|     * kotlinc: 0 ms
|     * creating KSP generated src jar: 3 ms
```

It takes around 367 ms, which again shows that `ksp` is faster by close to **30%** compared to kapt.


## Notes

This is a very small project, and the gains might seem small but in larger projects with many packages and targets and much larger source files, the 30% improvement (or perhaps even more) could compound into noticeable improvements in compilation times.
