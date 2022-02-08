import WktValidator.isValidWkt
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.scalatest.funsuite.AnyFunSuite

class Tests extends AnyFunSuite{

  test("Verify isValidWkt function test1"){
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sparkSession = SparkSession.builder().appName("WKT_VALIDATOR").master("local[*]").getOrCreate()
    val result = WktValidator.isValidWkt(sparkSession, "src/test/resources/1.csv")
    assert(result == true)
  }

  test("Verify isValidWkt function test2"){
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sparkSession = SparkSession.builder().appName("WKT_VALIDATOR").master("local[*]").getOrCreate()
    val result = WktValidator.isValidWkt(sparkSession, "src/test/resources/2.csv")
    assert(result == false)
  }

}
