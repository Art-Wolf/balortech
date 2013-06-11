#!/usr/bin/env ruby

require "rubygems"
require "couchbase"
require "rainbow"
require "pp"

system 'clear'
puts "--------------------------------------------------------------------------".bright.foreground(:red)
puts "## Couchbase Retrieval Operations".bright.foreground(:red)
puts "--------------------------------------------------------------------------".bright.foreground(:red)
puts


# establish connection, all different ways
cb = Couchbase.connect
cb.quiet = true

user_data1 = {
  :doctype => "learn",
  :username => "jsmith",
  :name => "John Smith",
  :email => "jsmith@email.com",
  :password => "p4ssw0rd",
  :logins => 0
}

user_data2 = {
  :doctype => "learn",
  :username => "xsmith",
  :name => "Xavier Smith",
  :email => "xsmith@email.com",
  :password => "p4ssw0rd",
  :logins => 0
}

# initialize the documents
cb.set(user_data1[:username], user_data1)
cb.set(user_data2[:username], user_data2)
