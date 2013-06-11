class Restaurant < Couchbase::Model
  attribute :name, :description
  attribute :country, :state, :city, :address
  attribute :phone
  attribute :geo
  attribute :updated

  view :all, :limit => 31
  view :by_beer
  view :by_cocktails
  view :by_food
  view :by_specials
  view :by_country, :include_docs => false, :group => true
  view :points, :spatial => true

  def full_address
    [country, state, city, address].reject(&:blank?).join(', ')
  end

  def location
    [country, state].reject(&:blank?).join(', ')
  end
end
