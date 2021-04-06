package io.rslowly.ctap.project

import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult

object MainApp extends App {
  val config   = ConfigFactory.load()
  val dbConfig = config.getConfig("database")

  migrateDatabase(
    dbConfig.getString("url"),
    dbConfig.getString("user"),
    dbConfig.getString("password"),
    dbConfig.getString("schema")
  )

  def migrateDatabase(url: String, user: String, password: String, schema: String): MigrateResult = {
    val flyway = Flyway.configure()
      .locations("classpath:database/migration")
      .baselineOnMigrate(true)
      .dataSource(url, user, password)
      .schemas(schema)
      .load()
    flyway.migrate()
  }
}
