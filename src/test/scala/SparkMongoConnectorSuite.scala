import com.mongodb.spark.MongoSpark
import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.FunSuite

class SparkConnectorTest extends FunSuite {

  val appConf = ConfigFactory.load()
  val inUrl = appConf.getString("appConfig.mongo.inUrl");
  val outUrl = appConf.getString("appConfig.mongo.outUrl");


  val conf = new SparkConf()
  conf.setMaster("local");
  conf.setAppName("testClass");
  val sc = new SparkContext(conf);
  SparkMongoConnector("testClass", inUrl, outUrl);

  test("SaprkContext in not null") {
    assert(sc != null);
  }

  test("Spark is isConnected to Mongo") {
    SparkMongoConnector("testClass", inUrl, outUrl);
    assert(MongoSpark != null);
  }

  test("Mongo Collection not empty") {
    SparkMongoConnector.loadRdd(sc);
    assert(SparkMongoConnector.getCount() > 0)
  }
}

