require "rubygems"
require "sinatra"
require "oauth"
#require "oauth/consumer"

enable :sessions

before do
  session[:oauth] ||= {}  
  @consumer = OAuth::Consumer.new("kjashdkhsa981293hk", "kajshdi2y9hjhwegf8wekjh", :site => "https://agree2")
  
  if !session[:oauth][:request_token].nil? && !session[:oauth][:request_token_secret].nil?
    @request_token = @consumer.get_request_token(:oauth_callback => "http://127.0.0.1:3000/oauth/callback")
    session[:request_token]i = @request_token
    q2, session[:oauth][:request_token_secret])
  end
  
  if !session[:oauth][:access_token].nil? && !session[:oauth][:access_token_secret].nil?
    @access_token = OAuth::AccessToken.new(@consumer, session[:oauth][:access_token], session[:oauth][:access_token_secret])
  end
end

get "/" do
  if @access_token
    erb :ready
  else
    erb :start
  end
end

get "/request" do
  @request_token = @consumer.get_request_token
  session[:oauth][:request_token] = @request_token.token
  session[:oauth][:request_token_secret] = @request_token.secret
  redirect @request_token.authorize_url
end

get "/callback" do
  @access_token = @request_token.get_access_token :oauth_verifier => params[:oauth_verifier]
  session[:oauth][:access_token] = @access_token.token
  session[:oauth][:access_token_secret] = @access_token.secret
  redirect "/"
end

get "/logout" do
  session[:oauth] = {}
  redirect "/"
end

enable :inline_templates

__END__

@@ start
<a href="/request">PWN OAuth</a>

@@ ready
OAuth PWND. <a href="/logout">Retreat!</a>
