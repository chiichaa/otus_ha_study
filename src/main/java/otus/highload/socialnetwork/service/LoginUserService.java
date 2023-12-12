package otus.highload.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.highload.socialnetwork.openapi.model.LoginPost200Response;
import otus.highload.socialnetwork.openapi.model.LoginPostRequest;

import javax.security.auth.message.AuthException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginUserService {

    @Autowired
    private final DataSource ds;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtProvider jwtProvider;


    public LoginPost200Response getUserToken(LoginPostRequest request) throws AuthException {
        if (checkUserPassword(request)) {
            String accessToken = jwtProvider.generateAccessToken(request.getId());
            LoginPost200Response loginPost200Response = new LoginPost200Response();
            loginPost200Response.token(accessToken);
            return loginPost200Response;
        } else {
            throw new AuthException("incorrect user or password");
        }
    }

    @Transactional
    public boolean checkUserPassword(LoginPostRequest loginRequest) {
        final String getUserMetadataQuery = "select id, password from users_metadata where id = ?::uuid";
        try (Connection con = ds.getConnection(); PreparedStatement statement = con.prepareStatement(getUserMetadataQuery)) {
            statement.setString(1, loginRequest.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return passwordEncoder.matches(loginRequest.getPassword(), resultSet.getString("password"));
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error("get User error", e);
            return false;
        }
    }

}
