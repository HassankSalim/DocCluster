# DocCluster

## Clustering scraped techcrunch articles with Spark

### Data Collecting Part

1. [singletechcrunchpaper.py](https://github.com/HassankSalim/DocCluster/blob/master/pythonFile/singletechcrunchpaper.py)
    * Python script to scrap a single TechCrunch Page / Article and write to MongoDb hosted in mlab

2. [techcrunch.py](https://github.com/HassankSalim/DocCluster/blob/master/pythonFile/techcrunch.py)
    * Find all the latest post url and pass it to singletechcrunchpaper.py.

3. [scrapyTechCrunch.sh](https://github.com/HassankSalim/DocCluster/blob/master/pythonFile/scrapyTechCrunch.sh)
    * Script for the crontab job, run excatly one time everyday.

### Data Read Part

1. [SparkMongoConnector.scala](https://github.com/HassankSalim/DocCluster/blob/master/src/main/scala/SparkMongoConnector.scala)
    * Scala singleton class to connect and perform basic operation on data

#### Technology Used

1. Python libs
    * [Scrapy](https://scrapy.org/)
    * [Pymongo](https://api.mongodb.com/python/current/)

2. DB Used
    * [MongoDB](https://www.mongodb.com/)

3. DB Connector
    * [Spark MongoDB Connector](https://docs.mongodb.com/spark-connector/current/)

4. Data Processing 
    * [Apache Spark](https://spark.apache.org/)
    
5. Os scheduling
    * [Crontab](https://www.computerhope.com/unix/ucrontab.htm)

### To Run files needed

1. application.conf file which contains the mongoDb username and password and link
