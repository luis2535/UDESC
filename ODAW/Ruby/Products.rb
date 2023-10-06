class Products < ApplicationRecord
end


p = Products.new
p.name = "Some Book"
puts p.name