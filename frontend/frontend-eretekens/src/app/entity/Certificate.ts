import { IApplication } from "./Application";

 export interface ICertificate{
    id: number;
    genderApplicant: string;
    jobTitleOption : string;
    management : string;
    totYearService : number;
    application: IApplication;

 }