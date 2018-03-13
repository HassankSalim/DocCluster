import scrapy
import re
from scrapy.crawler import CrawlerProcess
from pymongo import MongoClient
import datetime

class SingleTechCrunchSpider(scrapy.Spider):
	name = "SingleTechSpider"
	# start_urls = ['https://techcrunch.com/2017/12/12/facebook-bats-back-after-a-second-former-exec-accuses-it-of-negatively-impacting-society/',]
	start_urls = ['https://techcrunch.com/2017/12/29/you-can-now-pick-up-an-imac-pro-in-store-for-4999-and-up/']

	some_tag = '<\w+[^>]*>(.*?)</\w+>'
	# close_p_tag = '</p>'
	# open_p_tag = '<p[^>]*>'
	tag = '<\w+[^>]*>|</\w+>'
	client = MongoClient('localhost', 27017)

	db = client.techcrunch_db
	collection = db.techcrunch_posts

	def parse(self, response):
		div_contents = response.css(r'div.article-entry')
		result = ""
		for i in div_contents.css('p').extract():
			# no_p_tag = re.split('%s|%s'%(self.close_p_tag, self.open_p_tag), '', i)
			no_tags = re.split(self.tag, i)
			# re.split(self.some_tag, no_p_tag)
			result += ''.join(no_tags) + '\n '

		post = { 'article' : result, 'day' : datetime.datetime.utcnow().day, 'month' : datetime.datetime.utcnow().month, 'year' : datetime.datetime.utcnow().year }
		post_id = self.collection.insert_one(post).inserted_id

		return { 'result':result, 'post_id': post_id }


if __name__ == '__main__':
	# test = 'https://techcrunch.com/2017/12/11/max-levchins-affirm-raised-200-million-at-nearly-2-billion-valuation/'
	process = CrawlerProcess({'USER_AGENT': 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)'})
	# process.crawl(TechCrunchSpider, start_urls=[test, ])
	process.crawl(SingleTechCrunchSpider)
	process.start()