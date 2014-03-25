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

bar_data1 = {
  :doctype => "bar",
  :name => "The Banshee",
  :address => "934 Dorchester Ave, Boston, MA 02125",
  :phone => "(617) 436-9747"
}

# initialize the documents
cb.set(bar_data1[:name], bar_data1)

# retrieve the document and output
puts "Retrieve Doc and Inspect".bright.foreground(:red)
puts
doc = cb.get(bar_data1[:name])
pp doc
puts
puts

# retrieve extended info
puts "--------------------------------------------------------------------------".bright.foreground(:red)
puts "Retrieve Doc Extended Info and Inspect".bright.foreground(:red)
puts
doc, flags, cas = cb.get(bar_data1[:name], :extended => true)
pp doc
puts
puts "cas = #{cas}"
puts
puts
puts "--------------------------------------------------------------------------".bright.foreground(:red)
puts
