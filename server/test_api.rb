require 'rubygems'
require 'sinatra'
require 'json'

put '/users/:id' do |n|
  data = JSON.parse(request.body.read)
  "Got #{data} for user #{n}"
end
