export interface ItemDtoForProduct {
  itemId: number;
  type: ClothingType;
  size: ClothingSize;
  state: ItemState;
  genderSuitability: GenderSuitability;
  imageUrl: string;
  description: string;
  status: ItemStatus;
  volunteerId: number ; // Optional field, depending on the backend
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
  UNISEX = 'Unisex'
}

export enum ItemStatus {
  REJECTED = 'مرفوض',
  PENDING = 'معلق',
  APPROVED = 'تم_الموافقه'
}

