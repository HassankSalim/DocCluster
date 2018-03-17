import com.mongodb.spark._
import com.mongodb.spark.config.ReadConfig
import org.apache.spark.{SparkConf, SparkContext}
import org.bson.Document


object TestScript {


  def main(args: Array[String]): Unit = {

    /* Create the SparkSession.
     * If config arguments are passed from the command line using --conf,
     * parse args for the values to set.
     */
    import org.apache.spark.sql.SparkSession

    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)

    val mongoUrl = "mongodb://HassankSalim:Hassan%401996@ds133017.mlab.com:33017/techcrunch_db.techcrunch_posts"

    val spark = SparkSession.builder()
      .master("local")
      .appName("MongoSparkConnectorIntro")
      .config("spark.mongodb.input.uri", mongoUrl)
      .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.mongo_out")
      .getOrCreate()


  //Learning to write to mongodb

  //val documents = sc.parallelize((1 to 10).map(i => Document.parse(s"{test: $i}")))
  //MongoSpark.save(documents)

    val rdd = MongoSpark.load(sc)

    println("=============================================")

    println(rdd.count())
    println(rdd.first.toJson)

    println("=============================================")


  }

}
