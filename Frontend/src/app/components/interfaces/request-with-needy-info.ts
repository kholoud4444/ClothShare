export interface NeedyInfo {
  userId: number;
  firstName: string;
  lastName: string;
  phone: string;
  gender: string;
  location: string;
}

export interface RequestWithNeedyInfo {
  requestStatus: string;
  reason: string;
  date: string;
  needyInfo: NeedyInfo;
  requestId: number;
}
