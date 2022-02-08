import com.vividsolutions.jts.io.{ParseException, WKTFileReader, WKTReader}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}


object WktValidator {

  def isValidWkt(sparkSession: SparkSession, wktFile: String, hasHeader: Boolean = true): Boolean = {
    val df = sparkSession.read
      .format("csv")
      .option("header", hasHeader)
      .option("multiLine", "true")
      .option("delimiter", ",")
      .load(wktFile)
    isValidWkt(df.toDF("WKT", "ID"))
  }

  def isValidWkt(df: DataFrame): Boolean = {
    val collection = df.select("WKT", "ID").rdd.map(x => (x.getString(0), x.getString(1)))
    val count = collection.map { element =>
      try {
        val wtxReader = new WKTReader()
        val shape = wtxReader.read(element._1)
        if (!shape.isValid) {
          println(s"Invalid Polygon Id - ${element._2}")
          1
        }
        else 0
      } catch {
        case e: Exception =>
          println(s"Invalid Polygon Id - ${element._2}")
          1
      }
    }.fold(0)((a, b) => a + b)
    if (count == 0) true else false
  }
}
