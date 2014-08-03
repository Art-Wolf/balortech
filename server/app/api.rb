module Tab
  class API < Grape::API
    prefix 'api'
    format :json
    mount ::Tab::Bar
    mount ::Tab::Menu
    add_swagger_documentation api_version: 'v1'
  end
end
