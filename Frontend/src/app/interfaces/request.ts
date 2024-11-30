export interface Request {
  requestId: number;
  requestStatus: string;
  reason: string;
  date: string;
  needyId: number;
  itemData: {
    type: string;
    size: string;
    state: string;
    genderSuitability: string;
    imageUrl: string;
    description: string;
    status: string;
    volunteerId: number;
    itemId: number;
  };
}
