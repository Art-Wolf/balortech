require 'rubygems'
require 'rest_client'
require 'json'

jdata = JSON.generate(["p4ssw0rd"])
response = RestClient.put 'http://localhost:4567/user/jsmith', jdata, {:content_type => :json}

puts response.body

