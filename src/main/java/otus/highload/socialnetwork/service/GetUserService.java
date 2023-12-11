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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class GetUserService {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;

    @Autowired
    DataSource ds;

    @Transactional
    public User get(String userId) {
        final String selectUserInfo = "select id, first_name, second_name, birthdate, biography, city from users where id = ?::uuid";
        try (Connection con = ds.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(selectUserInfo)) {
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.id(resultSet.getString("id"));
                user.firstName(resultSet.getString("first_name"));
                user.secondName(resultSet.getString("second_name"));
                user.birthdate(LocalDate.from(dateFormatter.parse(resultSet.getString("birthdate"))));
                user.biography(resultSet.getString("biography"));
                user.city(resultSet.getString("city"));
                return user;
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException e) {
            log.error("get User error", e);
            throw new RuntimeException(e);
        }
    }

}
