// item-dto.model.ts
// enums.ts
export enum ClothingType {
  SHIRT = 'SHIRT',
  PANTS = 'PANTS',
  // add other clothing types here
}

export enum ClothingSize {
  SMALL = 'SMALL',
  MEDIUM = 'MEDIUM',
  LARGE = 'LARGE',
  // add other sizes here
}

export enum ItemState {
  NEW = 'NEW',
  USED = 'USED',
  // add other states here
}

export enum GenderSuitability {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  UNISEX = 'UNISEX',
}

export enum ItemStatus {
  PENDING = 'معلق',
  APPROVED = 'تم_الموافقه',
  REJECTED = 'مرفوض'
}



export interface RequestVolunteerHistory {
  item: {
    type: ClothingType;
    size: ClothingSize;
    state: ItemState;
    genderSuitability: GenderSuitability;
    imageUrl: string;
    description: string;
    status: ItemStatus;
    volunteerId?: number; // Optional field for volunteer ID
  };
  volunteerName: string; // Name of the volunteer associated with the item
  itemId: number;        // Unique ID of the item
}
export interface ApiResponse {
  content: RequestVolunteerHistory[]; // Array of RequestVolunteerHistory items
  totalElements: number;
  totalPages: number;
  pageNumber: number;
  pageSize: number;
  isLast: boolean;
}

