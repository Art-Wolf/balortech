require 'sinatra'
require 'json'
require "rubygems"
require "couchbase"
require "rainbow"
require "sinatra/base"
require_relative "../database/CouchbaseConnection"

class BarService < Sinatra::Base

def initialize
 super
 @couchbase ||= CouchbaseConnection.new()
end

get '/barSearch/:name' do |bar_name|
  content_type :json

  bars = @couchbase.connection("bars")
  bar = bars.get{bar_name, :quiet => true)

  if bar.nil?
    response.body = { "error_id"=> 1, "error_content"=> "The bar #{bar_name} was not found." }.to_json
    response.status = 404
  else
    bar = bars.get(bar_name,:quiet => true)
    response.body = user
    response.status = 200
  end
end
