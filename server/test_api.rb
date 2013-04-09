require 'rubygems'
require 'sinatra'
require 'json'
 
post '/test' do
  data = JSON.parse(request.body.read)
  data.to_json
end

