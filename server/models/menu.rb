module Tab
  module Models
    class Menu
      include Mongoid::Document
      store_in collection: "menus", database: "app27870978"

      field :name, type: String, description: "Name of bar."
      field :item_name, type: String, description: "Name of the item."
      field :item_description, type: String, description: "Description of the item."
      field :item_price, type: String, description: "Price of the item."
    end
  end
end
