import com.mongodb.spark._
import com.mongodb.spark.rdd.MongoRDD
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.bson.Document


object SparkMongoConnector {

  var sparkSess : SparkSession = _
  var rdd : MongoRDD[Document] = _
  val conf = ConfigFactory.load();

  val username = conf.getString("appConfig.mongo.user");
  val password = conf.getString("appConfig.mongo.password");

  def mongoUrlConstructor(url: String): String = {
    return s"mongodb://$username:$password@$url"
  }

  def apply(appName: String, inUrl: String, outUrl : String = "") = {

    val spark = SparkSession.builder()
      .master("local")
      .appName(appName)
      .config("spark.mongodb.input.uri", mongoUrlConstructor(inUrl))
      .config("spark.mongodb.output.uri", mongoUrlConstructor(outUrl))
      .getOrCreate()

  }

  def loadRdd(sc : SparkContext) = rdd = MongoSpark.load(sc)

  def getCount() = rdd.count()

  def getRdd = rdd

  def filter(customFilter : (Document) => Boolean, mongoRDD: MongoRDD[Document]) = { mongoRDD.filter(doc => customFilter(doc)) }
}
