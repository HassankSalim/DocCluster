import com.mongodb.spark._
import com.mongodb.spark.rdd.MongoRDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.bson.Document


object SparkMongoConnector {

  var sparkSess : SparkSession = _
  var rdd : MongoRDD[Document] = _

  def apply(appName: String, inUrl: String, outUrl : String = "") = {

    val spark = SparkSession.builder()
      .master("local")
      .appName(appName)
      .config("spark.mongodb.input.uri", inUrl)
      .config("spark.mongodb.output.uri", outUrl)
      .getOrCreate()

  }

  def loadRdd(sc : SparkContext) = rdd = MongoSpark.load(sc)

  def getCount() = rdd.count()

  def getRdd = rdd

  def filter(customFilter : (Document) => Boolean, mongoRDD: MongoRDD[Document]) = { mongoRDD.filter(doc => customFilter(doc)) }
}
