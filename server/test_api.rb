require 'rubygems'
require 'sinatra'
require 'json'
require "couchbase"
require "rainbow"
require "pp"

put '/user/:id' do |n|
  data = JSON.parse(request.body.read)

  puts "Checking #{n} with password '#{data}'"

  user_data1 = {
    :doctype => "",
    :username => "#{n}",
    :name => "",
    :email => "",
    :password => "",
    :logins => 0
  }

  cb = Couchbase.connect
  cb.quiet = true
  doc = cb.get(user_data1[:username])

  if doc["password"].eql? "#{data}"
  	"Autheticated."
  else
	"Invalid password."
  end
end
