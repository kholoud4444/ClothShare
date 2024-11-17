export interface IProduct {
  id: number,
  code: string,
  name: string,
  description: string,
  image: string,
  price: number,
  category: string,
  quantity: number,
  inventoryStatus: ProductInventoryStatus,
  rating: number
}

export enum ProductInventoryStatus {
  "IN_STOCK" = "IN STOCK",
  "OUT_OF_STOCK" = "OUT OF STOCK",
  "LOW_STOCK" = "LOW STOCK"
}
