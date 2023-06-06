export enum ApplicationState {
        GOEDKEURING_1 = "GOEDKEURING_1",
        GOEDKEURING_2 = "GOEDKEURING_2",
        GOEDKEURING_3 = "GOEDKEURING_3",
        GOEDKEURING_4 = "GOEDKEURING_4", 
        GOEDKEURING_5 = "GOEDKEURING_5", 
        ADVIES = "ADVIES",
        BESLUIT = "BESLUIT",
        BESLUIT_VERTALEN = "BESLUIT_VERTALEN",
        OORKONDE_OPMAKEN = "OORKONDE_OPMAKEN",
        KLAAR = "KLAAR",
        AFGEKEURD = "AFGEKEURD"
}

export function getName( state: ApplicationState) {
        switch(state){
                case ApplicationState.GOEDKEURING_1:
                        return "Goedkeuring 1/5 (ABB)";
                        break;
                case ApplicationState.GOEDKEURING_2:
                        return "Goedkeuring 2/5 (Minister)";
                        break;
                case ApplicationState.GOEDKEURING_3:
                        return "Goedkeuring 3/5 (Eerste Minister)";
                        break;
                case ApplicationState.GOEDKEURING_4:
                        return "Goedkeuring 4/5 (Kanselarij)";
                        break;
                case ApplicationState.GOEDKEURING_5:
                        return "Goedkeuring 5/5 (Koning)";
                        break;
                case ApplicationState.ADVIES:
                        return "Advies";
                        break;
                case ApplicationState.BESLUIT:
                        return "Besluit";
                        break;
                case ApplicationState.BESLUIT_VERTALEN:
                        return "Besluit vertalen";
                        break;
                case ApplicationState.OORKONDE_OPMAKEN:
                        return "Oorkonde opmaken";
                        break;
                case ApplicationState.KLAAR:
                        return "Klaar";
                        break;
                case ApplicationState.AFGEKEURD:
                        return "Afgekeurd";
                        break;
                default:
                        return;
                break;
        }
}


export function getState(name:string) {
        switch(name){
                case "Goedkeuring 1/5 (ABB)":
                        return ApplicationState.GOEDKEURING_1;
                        break;
                case "Goedkeuring 2/5 (Minister)":
                        return ApplicationState.GOEDKEURING_2;
                        break;
                case "Goedkeuring 3/5 (Eerste Minister)":
                        return ApplicationState.GOEDKEURING_3;
                        break;
                case "Goedkeuring 4/5 (Kanselarij)":
                        return ApplicationState.GOEDKEURING_4;
                        break;
                case "Goedkeuring 5/5 (Koning)":
                        return ApplicationState.GOEDKEURING_5;
                        break;
                case "Advies":
                        return ApplicationState.ADVIES;
                        break;
                case "Besluit":
                        return ApplicationState.BESLUIT;
                        break;
                case "Besluit vertalen":
                        return ApplicationState.BESLUIT_VERTALEN;
                        break;
                case "Oorkonde opmaken":
                        return ApplicationState.OORKONDE_OPMAKEN;
                        break;
                case "Klaar":
                        return ApplicationState.KLAAR;
                        break;
                case "Afgekeurd":
                        return ApplicationState.AFGEKEURD;
                        break;
                default:
                        return;
                break;
        }
}