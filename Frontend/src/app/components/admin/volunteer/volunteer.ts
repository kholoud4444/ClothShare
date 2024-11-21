export interface UserResponseDetails {
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

export interface PageDto<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  currentPage: number;
}
