ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10"
val sparkVersion = "3.1.2"
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "com.vividsolutions" % "jts-io" % "1.14.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
lazy val root = (project in file("."))
  .settings(
    name := "wkt_validator"
  )
