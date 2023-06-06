import { UserRole } from "../enum/user-role";

export interface User {
    id?: number;
    email: string;
    password: string;
    role: UserRole | null;
    firstname : string;
    lastname : string;
}