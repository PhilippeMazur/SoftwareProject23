export interface IABBCareer {
    nationalNumber: string;
    currentOccupation: string;
    currentFunctionTitle: string;
    currentGrade: string;
    currentSalary: string;
    career: ICareerItem[];
  }

  export interface ICareerItem {
    functionTitle: string;
    grade: string;
    from: Date;
    until: Date | null;
    percentage: number;
    statute: string;
  }
