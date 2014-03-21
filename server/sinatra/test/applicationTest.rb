ENV['RACK_ENV'] = 'test'

require_relative '../controller/application'
require 'minitest/autorun'
require 'rack/test'

include Rack::Test::Methods

def app
  Sinatra::Application
end

describe "Bar Search" do
  it "should return json" do
    get '/barSearch/Banshee'
    last_response.headers['Content-Type'].must_equal 'application/json;charset=utf-8'
  end
 
  it "should return the correct info about the Banshee" do
    get '/barSearch/Banshee'
    bar_info = { bar_id: 1, bar_name: 'Banshee' }
    last_response.body.must_equal bar_info.to_json
    last_response.status.must_equal 200
  end

  it "should warn if the bar is not found" do
    get '/barSearch/Unknown'
    error_info = { error_id: 1, error_content: 'The bar Unknown was not found.' }
    last_response.body.must_equal error_info.to_json
    last_response.status.must_equal 404
  end
end
