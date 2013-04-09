require 'rubygems'
require 'rest_client'
require 'json'

jdata = JSON.generate(["test"])
response = RestClient.put 'http://localhost:4567/users/123', jdata, {:content_type => :json}

puts response.body

