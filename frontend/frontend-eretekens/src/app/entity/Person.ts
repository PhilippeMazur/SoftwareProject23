export interface IPerson
{
    id?: number;
    nationalNumber: string | null,
    firstName: string | null,
    lastName: string | null,
    dateOfBirth: Date | null,
    placeOfBirth: string | null,
    street: string | null,
    houseNumber: string | null,
    postalCode: string | null,
    city: string | null,
}
