module Tab
  class Menu < Grape::API
    version 'v1'
    format :json

    resource :menu do
      desc "Retrieve a bar by name."
      get "/:name" do
        menu = Tab::Models::Menu.where({name: params[:name]})
        error! "Not Found", 404 unless menu
        menu.as_json
      end

      desc "Create a menu item for a bar" , params: Tab::Models::Menu.fields.dup.tap {|fields| fields.delete("_id") }
      post do
        menu = Tab::Models::Menu.create!( {
          name: params[:name],
          item_name: params[:item_name],
          item_description: params[:item_description],
          item_price: params[:item_price]
        } )

        menu.as_json
      end

      desc "Update a menu item by bar name and item name.", params: Tab::Models::Menu.fields.merge( "name" => {description: "Bar name.", required: true}, "item_name" => {description: "Item name.", required: true})
      put do
        menu = Tab::Models::Menu.find_by({name: params[:name], item_name: params[:item_name]})
        error! "Not Found", 404 unless menu
        values = {}
        values[:name] = params[:name] if params.key?(:name)
        values[:item_name] = params[:item_name] if params.key?(:item_name)
        values[:item_description] = params[:item_description] if params.key?(:item_description)
        values[:item_price] = params[:item_price] if params.key?(:item_price)
        menu.update_attributes!(values)
        menu.as_json
      end

      desc "Delete a menu item by bar name and item name", params: { "name" => {description: "Bar name.", required: true}, "item_name" =>  {description: "Item name.", required: true}}
      delete do
        menu = Tab::Models::Menu.find_by({name: params[:name], item_name: params[:item_name]})
        error! "Not Found", 404 unless menu
        menu.destroy
        menu.as_json
      end
    end
  end
end
