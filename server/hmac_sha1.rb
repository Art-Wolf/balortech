require 'base64'
require 'cgi'
require 'openssl'

key = '1234'
signature = 'abcdef'
puts CGI.escape(Base64.encode64("#{OpenSSL::HMAC.digest('sha1',key, signature)}\n"))

