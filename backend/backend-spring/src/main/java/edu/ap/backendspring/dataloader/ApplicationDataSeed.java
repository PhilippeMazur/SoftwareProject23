package edu.ap.backendspring.dataloader;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.enums.State;
import edu.ap.backendspring.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationDataSeed implements CommandLineRunner {

    @Autowired
    ApplicationRepository applicationRepository;
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {
        if (applicationRepository.count() == 0) {
            LocalDate birthdate1 = LocalDate.of(1990,2,12);
            LocalDate creationdate1 = LocalDate.of(2007,6,30);
            Application application1 = new Application("12345678900","Bob", "Dupont",birthdate1, "Antwerpen","Antwerpen",
                    "administratief_medewerker", "senior", "A0F1", "Developers", "neen",
                    5,2,"geen", "geen", State.GOEDKEURING_1,"A5", "burgerlijke ereteken", "geen", "onbekend", "onbekend", "onbekend", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
            applicationRepository.save(application1);
            LocalDate birthdate2 = LocalDate.of(1984,8,20);
            LocalDate creationdate2 = LocalDate.of(2020,4,26);
            Application application2 = new Application("12345678901","Megan", "Smos",birthdate2, "Rotterdam","Wuustwezel",
                    "administratief_medewerker", "senior", "D0U1", "Developers", "neen",
                    3,7,"geen", "geen", State.GOEDKEURING_1,"2C3", "burgerlijke ereteken", "geen", "onbekend", "onbekend", "onbekend", null, null, null, null, null , creationdate2, null, null, null, null, null, null, "distinction");
            applicationRepository.save(application2);
        }
    }


}

