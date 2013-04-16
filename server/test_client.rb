require 'rubygems'
require 'rest_client'
require 'json'
require 'base64'
require 'cgi'
require 'openssl'

access_key = 'jsmith'
secret_key = '1234abcdef'
signature = CGI.escape(Base64.encode64("#{OpenSSL::HMAC.digest('sha1',access_key, secret_key)}\n"))

time = Time.now
date = time.to_date


jdata = JSON.generate(["p4ssw0rd"])
response = RestClient.put 'http://localhost:4567/user/jsmith', jdata, {:content_type => :json}

puts response.body

