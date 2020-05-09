
plugins {
    scala
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.scala-lang", "scala-library", "2.13.2")
    implementation("com.google.guava", "guava", "28.2-jre")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.0")
}

tasks.withType<ScalaCompile> {
    // scalaCompileOptions.useAnt = false
}

application {
    mainClassName = "ch.ctap.project.MainApp"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
