require 'sinatra'
require 'json'

get '/barSearch/:name' do
  content_type :json

  if params[:name] != "Banshee"
    { "error_id"=> 1, "error_content"=> "The bar Unknown was not found." }.to_json
  else
    { "bar_id" => 1, "bar_name" => params[:name] }.to_json
  end
end
