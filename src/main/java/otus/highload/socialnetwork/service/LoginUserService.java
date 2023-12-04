package otus.highload.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Transactional
//    public boolean loginUser(LoginRequest loginRequest) {
//        final String getUserMetadataQuery = "select id, password from users_metadata where id = ?";
//        try (Connection con = ds.getConnection(); PreparedStatement statement = con.prepareStatement(getUserMetadataQuery)) {
//            statement.setString(1, loginRequest.getId());
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return BcryptUtil.bcryptHash(loginRequest.getPassword()).equals(resultSet.getString("password"));
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            log.error("get User error", e);
//            return false;
//        }
//    }

}
