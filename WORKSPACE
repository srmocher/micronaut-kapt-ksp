load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "5.0"

RULES_JVM_EXTERNAL_SHA = "8c3cd0ce6aa3dd8c01a414385e0a3807c7a14c769ca0aa3c53fb135c91f9198c"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/5.0/rules_jvm_external-%s.tar.gz" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = "5766f1e599acf551aa56f49dab9ab9108269b03c557496c54acaf41f98e2b8d6",
    url = "https://github.com/bazelbuild/rules_kotlin/releases/download/v1.9.0/rules_kotlin-v1.9.0.tar.gz",
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()  # if you want the default. Otherwise see custom kotlinc distribution below

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()  # to use

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven_micronaut4",
    artifacts = [
        "io.micronaut:micronaut-inject:4.3.0",
        "io.micronaut:micronaut-inject-kotlin:4.3.0",
        "jakarta.inject:jakarta.inject-api:2.0.0",
        "jakarta.validation:jakarta.validation-api:3.1.0",
        "io.micronaut:micronaut-runtime:4.3.0",
        "io.micronaut:micronaut-core:4.3.0",
        "io.micronaut:micronaut-context:4.3.0",
    ],
    fetch_sources = True,
    maven_install_json = "@//:maven_micronaut4_install.json",
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
        "https://packages.confluent.io/maven/",
        "https://jitpack.io",
        "https://repo.kotlin.link",
        "https://maven.pkg.jetbrains.space/mipt-npm/p/sci/maven",
        "https://mvnrepository.com/artifact/",
    ],
)

maven_install(
    artifacts = [
        # This line is an example coordinate, you'd copy-paste your actual dependencies here
        # from your build.gradle or pom.xml file.
        "org.ow2.asm:asm:9.7",
        "com.google.code.gson:gson:2.12.0",
    ],
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

maven_install(
    name = "maven_micronaut3",
    artifacts = [
        "io.micronaut:micronaut-inject-java:3.10.2",
        "io.micronaut:micronaut-runtime:3.10.2",
        "io.micronaut:micronaut-validation:3.10.2",
        "javax.inject:javax.inject:1",
        "jakarta.inject:jakarta.inject-api:2.0.0",
        "jakarta.validation:jakarta.validation-api:3.1.0",
    ],
    fetch_sources = True,
    maven_install_json = "@//:maven_micronaut3_install.json",
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
        "https://packages.confluent.io/maven/",
        "https://jitpack.io",
        "https://repo.kotlin.link",
        "https://maven.pkg.jetbrains.space/mipt-npm/p/sci/maven",
        "https://mvnrepository.com/artifact/",
    ],
)

load("@maven_micronaut4//:defs.bzl", micronaut4 = "pinned_maven_install")

micronaut4()

load("@maven_micronaut3//:defs.bzl", micronaut3 = "pinned_maven_install")

micronaut3()

load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()
