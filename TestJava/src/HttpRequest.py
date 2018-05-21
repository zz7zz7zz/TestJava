import urllib 
import urllib2
url = 'http://xz.noticiasnews.io/gateweb/qa/getConfig'
headers = { 'User-Agent' : 'Android' }   
body = {'tk' : 'bb33258f5420f2e832dd6aa79c93e56f', 
		'product_id' : 'qa',   
        'sign' : '3229117b019be20b932a41314b8621c9',
		'app_lan' : 'Spanish',   
        'app_ver' : '1.0.0.0.0.0',
		'timestamp' : '1526894171655',
        'promotion' : 'gp',
        'api_ver' : '1.0.0',   
        'request_time' : '1526894171',
		'platform' : 'android',
        'network' : '1',
        'brand' : 'google',   
        'uid' : 'e29e5bb911e03678',
        'ver' : '',   
        'model' : 'Nexus 5'}
req = urllib2.Request(url, urllib.urlencode(body),headers)
response = urllib2.urlopen(req)
print response.read()