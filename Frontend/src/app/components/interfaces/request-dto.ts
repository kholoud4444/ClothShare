export interface RequestDto {
  requestId: number;
  date: string; // Use string for LocalDate (ISO 8601 format, e.g., "2024-11-22")
  status: RequestStatus;
  reason: string;
  needyId: number;
  itemId: number;
}

export enum RequestStatus {
  مرفوض = "مرفوض",
  قيد_المراجعه = "قيد_المراجعه",
  تم_الموافقه = "تم_الموافقه"
}
