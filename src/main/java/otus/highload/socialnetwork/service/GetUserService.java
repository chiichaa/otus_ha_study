package otus.highload.socialnetwork.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.highload.socialnetwork.openapi.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class GetUserService {

    @Autowired
    DataSource ds;

//    @Transactional
//    public User get(String userId) {
//        final String selectUserInfo = "select id, first_name, second_name, birthdate, biography, city from users where id = ?::uuid";
//        try (Connection con = ds.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(selectUserInfo)) {
//            preparedStatement.setObject(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return GetUserResponse.builder()
//                        .id(resultSet.getString("id"))
//                        .firstName(resultSet.getString("first_name"))
//                        .secondName(resultSet.getString("second_name"))
//                        .birthdate(resultSet.getString("birthdate"))
//                        .biography(resultSet.getString("biography"))
//                        .city(resultSet.getString("city"))
//                        .build();
//            } else {
//                throw new NoSuchElementException();
//            }
//        } catch (SQLException e) {
//            log.error("get User error", e);
//            throw new RuntimeException(e);
//        }
//    }

}
