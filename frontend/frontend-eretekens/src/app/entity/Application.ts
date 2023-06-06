import { ApplicationState } from "../enum/application-state";

export interface IApplication
{
    id?: number;
    nationalRegisterNr: string | null,
    firstname: string | null,
    lastname : string | null,
    birthplace: string | null,
    birthdate: Date | null,
    city: string | null,
    mainProfession: string | null,
    jobTitle: string | null,
    gradeOrRank: string | null,
    initiator: string | null,
    salaryScale: string | null,
    distinctionsReceived: string | null,
    totYearService : number | null,
    totMonthService : number | null,
    resultEvaluation: string | null,
    sanctions: string | null,
    state: ApplicationState | null,
    proposedHonoraryDistinction: string | null,
    reportAboutInvolved: string| null,
    decision: string| null,
    decisionTranslated: string| null,
    advice: string| null,
    dateCreated: Date | null
    approvalABB: string| null,
    approvalABBDate: Date | null
    approvalMinister: string| null,
    approvalMinisterDate: Date | null
    approvalPrimeMinister: string| null,
    approvalPrimeMinisterDate: Date| null,
    approvalChancellery: string| null,
    approvalChancelleryDate: Date| null,
    approvalKing: string| null,
    approvalKingDate: Date| null,
    comment: string| null,
    proposedHonoraryDistinctionTitle: string | null
}
