class Bar
  include Mongoid::Document

  field :name, type => String
  field :city, type => String
  field :zipcode, type => String

end
