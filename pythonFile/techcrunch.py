import scrapy
import re
from scrapy.crawler import CrawlerProcess
from singletechcrunchpaper import SingleTechCrunchSpider


class TechCrunch(scrapy.Spider):
	"""docstring for TechCrunch"""
	name = "TechSpider"
	start_urls = ['https://techcrunch.com/']
	current_urls = []
	singlePageObj = SingleTechCrunchSpider()


	def parse(self, response):
		
		parseCounter = 0
		latest_ul = response.css(r'ul.river')

		for single_ul_list in latest_ul:
			li_urls  = single_ul_list.css('li.river-block::attr("data-permalink")').extract()

			for eachLink in li_urls:
				parseCounter += 1
				yield scrapy.Request(response.urljoin(eachLink), callback=self.singlePageObj.parse)

		return { 'EndResult': 'Number of parsed Docs is %s'%parseCounter }	

if __name__ == '__main__':
	process = CrawlerProcess({ 'USER_AGENT': 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)' })
	crawler_tech = TechCrunch()
	process.crawl(crawler_tech)
	process.start()
	
		