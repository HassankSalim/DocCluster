name := "DocCluster"

version := "0.1"

scalaVersion := "2.11.4"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"


libraryDependencies ++= {
  val sparkVer = "2.1.0"
  val typesafeConfig = "1.2.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer,
    "org.apache.spark" %% "spark-sql" % "2.1.0",
    "org.mongodb.spark" %% "mongo-spark-connector" % "2.1.0",
    "com.typesafe"           % "config" % typesafeConfig
  )
}
