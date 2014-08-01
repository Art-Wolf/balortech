module Tab
  class Bar < Grape::API
    version 'v1'
    format :json

    resource :bar do
      resource :list do

        desc "Get a list of bars."
        get do
          bar = Tab::Models::Bar.all()
          error! "Not Found", 404 unless bar
          bar.as_json
        end
      end

      desc "Create a bar" , params: Tab::Models::Bar.fields.dup.tap {|fields| fields.delete("_id") }
      post do
        bar = Tab::Models::Bar.create!( {
          name: params[:name],
          city: params[:city],
          zipcode: params[:zipcode]
        } )

        bar.as_json
      end

      desc "Retrieve a bar by name."
      params do
        requires :name, type: String, desc: "Bar name."
      end
      route_param :name do
        get do
          bar = Tab::Models::Bar.where({name: params[:name]})
          error! "Not Found", 404 unless bar
          bar.as_json
        end
      end

      desc "Update a bar by name.", params: Tab::Models::Bar.fields.merge( "name" => {description: "Bar name.", required: true})
      put do
        bar = Tab::Models::Bar.find_by({name: params[:name]})
        error! "Not Found", 404 unless bar
        values = {}
        values[:name] = params[:name] if params.key?(:name)
        values[:city] = params[:city] if params.key?(:city)
        values[:zipcode] = params[:zipcode] if params.key?(:zipcode)
        bar.update_attributes!(values)
        bar.as_json
      end

      desc "Delete a bar by name.", params: { "name" => {description: "Bar name.", required: true}}
      delete do
        bar = Tab::Models::Bar.find_by({name: params[:name]})
        error! "Not Found", 404 unless bar
        bar.destroy
        bar.as_json
      end
    end
  end
end
