module Tab
  module Models
    class Bar
      include Mongoid::Document
      store_in collection: "bars", database: "app27870978"

      field :name, type: String, description: "Name of bar."
      field :city, type: String, description: "City the bar is located in."
      field :zipcode, type: String, description: "Zipcode of the bar."
    end
  end
end
