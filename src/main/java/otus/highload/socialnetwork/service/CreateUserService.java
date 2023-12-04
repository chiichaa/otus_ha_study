package otus.highload.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.highload.socialnetwork.openapi.model.UserRegisterPost200Response;
import otus.highload.socialnetwork.openapi.model.UserRegisterPostRequest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserService {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;

    @Autowired
    private final DataSource ds;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserRegisterPost200Response register(UserRegisterPostRequest userRegisterRequest) {
        final String insertUserQuery = "insert into users (id, first_name, second_name, birthdate, biography, city) values (?, ?, ?, ?, ?, ?)";
        final String insertUserMetadataQuery = "insert into users_metadata (id, role, password) values (?, ?, ?)";
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

            PreparedStatement psCreateUserMetadata = con.prepareStatement(insertUserMetadataQuery);
            psCreateUserMetadata.setObject(1, userId);
            psCreateUserMetadata.setString(2, "user");
            psCreateUserMetadata.setString(3, passwordEncoder.encode(userRegisterRequest.getPassword()));
            psCreateUserMetadata.execute();
            psCreateUserMetadata.close();

            return new UserRegisterPost200Response()
                    .userId(userId.toString());
        } catch (SQLException e) {
            log.error("create User error", e);
            throw new RuntimeException(e);
        }
    }

}
