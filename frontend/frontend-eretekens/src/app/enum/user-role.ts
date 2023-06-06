export enum UserRole {
    DEVELOPER = "DEVELOPER",
    ONDERZOEKER_ABB = "ONDERZOEKER_ABB",
    ONDERZOEKER_MINISTER = "ONDERZOEKER_MINISTER",
    ONDERZOEKER_EERSTE_MINISTER = "ONDERZOEKER_EERSTE_MINISTER",
    ONDERZOEKER_KANSELARIJ = "ONDERZOEKER_KANSELARIJ",
    ONDERZOEKER_KONING = "ONDERZOEKER_KONING",
    INITIATIEFNEMER = "INITIATIEFNEMER",
    ADVISEUR = "ADVISEUR",
    BESLUITNEMER = "BESLUITNEMER",
    OORKONDEOPMAKER = "OOKONDEOPMAKER",
    VERTALER = "VERTALER"
}

export function getRoleName( role: UserRole) {
        switch(role){
                case UserRole.DEVELOPER:
                        return "Developer";
                        break;
                case UserRole.ONDERZOEKER_ABB:
                        return "Onderzoeker ABB";
                        break;
                case UserRole.ONDERZOEKER_MINISTER:
                        return "Onderzoeker Minister";
                        break;
                case UserRole.ONDERZOEKER_EERSTE_MINISTER:
                        return "Onderzoeker Eerste Minister";
                        break;
                case UserRole.ONDERZOEKER_KANSELARIJ:
                        return "Onderzoeker Kanselarij";
                        break;
                case UserRole.ONDERZOEKER_KONING:
                        return "Onderzoeker Koning";
                        break;
                case UserRole.INITIATIEFNEMER:
                        return "Initiatiefnemer";
                        break;
                case UserRole.ADVISEUR:
                        return "Adviseur";
                        break;
                case UserRole.BESLUITNEMER:
                        return "Besluitnemer";
                        break;
                case UserRole.OORKONDEOPMAKER:
                        return "Oorkondeopmaker";
                        break;
                case UserRole.VERTALER:
                        return "Vertaler";
                        break;
                default:
                        return;
                break;
        }
}


export function getRole(name:string) {
        switch(name){
                case "Developer":
                        return UserRole.DEVELOPER;
                        break;
                case "Onderzoeker ABB":
                        return UserRole.ONDERZOEKER_ABB;
                        break;
                case "Onderzoeker Minister":
                        return UserRole.ONDERZOEKER_MINISTER;
                        break;
                case "Onderzoeker Eerste Minister":
                        return UserRole.ONDERZOEKER_EERSTE_MINISTER;
                        break;
                case "Onderzoeker Kanselarij":
                        return UserRole.ONDERZOEKER_KANSELARIJ;
                        break;
                case "Onderzoeker Koning":
                        return UserRole.ONDERZOEKER_KONING;
                        break;
                case "Initiatiefnemer":
                        return UserRole.INITIATIEFNEMER;
                        break;
                case "Adviseur":
                        return UserRole.ADVISEUR;
                        break;
                case "Besluitnemer":
                        return UserRole.BESLUITNEMER;
                        break;
                case "Oorkondeopmaker":
                        return UserRole.OORKONDEOPMAKER;
                        break;
                case "Vertaler":
                        return UserRole.VERTALER;
                        break;
                default:
                        return;
                break;
        }
}

