export interface Product {
  id: number;
  title: string;
  description: string;
  category: string;
  price: number;
  discountPercentage: number;
  rating: number;
  stock: number;
  brand: string;
  sku: string;
  availabilityStatus: string;  // Add this field
  thumbnail: string;
  label : string ;
  icon : string ;
}
