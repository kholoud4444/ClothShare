export interface UserInterface {
  userId: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  nationalId: string;
  gender: string;
  birthDate: string;
  location: string;
  role: string;
}

export interface userResponseDetails {
  content: UserInterface[];
  totalElements: number;
  totalPages: number;
  pageNumber: number;
  pageSize: number;
  isLast: boolean;
}
