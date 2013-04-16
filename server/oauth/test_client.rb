require 'rubygems'
require 'rest_client'
require 'json'
require 'base64'
require 'cgi'
require 'openssl'

jdata = JSON.generate(["p4ssw0rd"])
response = RestClient.get 'http://localhost:4567/request'

puts response.body

