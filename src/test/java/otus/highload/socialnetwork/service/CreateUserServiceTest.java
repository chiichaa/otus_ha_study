package otus.highload.socialnetwork.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import otus.highload.socialnetwork.openapi.model.UserRegisterPost200Response;
import otus.highload.socialnetwork.openapi.model.UserRegisterPostRequest;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CreateUserServiceTest {

    private static String dataFilePath = ".data-for-tests/people.csv";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;


    @Autowired
    DataSource ds;

    @Test
    void register() {
        assertNotNull(ds);
//        read(dataFilePath);
    }

    public void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                String firstName = tempArr[0].split(" ")[0];
                String secondName = tempArr[0].split(" ")[1];
                UserRegisterPostRequest userRegisterPostRequest = new UserRegisterPostRequest();
                userRegisterPostRequest.firstName(firstName);
                userRegisterPostRequest.secondName(secondName);
                userRegisterPostRequest.birthdate(LocalDate.now());
                userRegisterPostRequest.biography(tempArr[1]);
                userRegisterPostRequest.city(tempArr[2]);
                userRegisterPostRequest.password("pswd");
                register(userRegisterPostRequest);
                System.out.println(userRegisterPostRequest);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Transactional
    public UserRegisterPost200Response register(UserRegisterPostRequest userRegisterRequest) {
        final String insertUserQuery = "insert into users (id, first_name, second_name, birthdate, biography, city) values (?, ?, ?, ?, ?, ?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement psCreateUser = con.prepareStatement(insertUserQuery);
            UUID userId = UUID.randomUUID();
            psCreateUser.setObject(1, userId);
            psCreateUser.setString(2, userRegisterRequest.getFirstName());
            psCreateUser.setString(3, userRegisterRequest.getSecondName());
            psCreateUser.setString(4, userRegisterRequest.getBirthdate().format(dateFormatter));
            psCreateUser.setString(5, userRegisterRequest.getBiography());
            psCreateUser.setString(6, userRegisterRequest.getCity());
            psCreateUser.execute();
            psCreateUser.close();

            return new UserRegisterPost200Response()
                    .userId(userId.toString());
        } catch (SQLException e) {
            log.error("create User error", e);
            throw new RuntimeException(e);
        }
    }
}