export interface ItemDto {
  type: ClothingType;
  size: ClothingSize;
  state: ItemState;
  genderSuitability: GenderSuitability;
  imageUrl: string;
  description: string;
  status: ItemStatus;
  volunteerId: number ; // Optional field, depending on the backend
}
export interface ItemDtoForEditItem {
  type: ClothingType;
  size: ClothingSize;
  state: ItemState;
  genderSuitability: GenderSuitability;
  description: string;
  status: ItemStatus;
  volunteerId: number ; // Optional field, depending on the backend
  itemId: number;
}

export enum ClothingType {
  FASHION = 'فستان',
  SHIRT = 'قميص',
  PANTS = 'بنطلون',
  JACKET = 'جاكيت'
}

export enum ClothingSize {
  SMALL = 'صغير',
  MEDIUM = 'متوسط',
  LARGE = 'كبير'
}

export enum ItemState {
  NEW = 'جديد',
  USED = 'مستعمل'
}

export enum GenderSuitability {
  MALE = 'ذكر',
  FEMALE = 'أنثى',
  UNISEX = 'مشتترك'
}

export enum ItemStatus {
  REJECTED = 'مرفوض',
  PENDING = 'معلق',
  APPROVED = 'تم_الموافقه'
}
