package otus.highload.socialnetwork.delegates;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import otus.highload.socialnetwork.openapi.api.UserApiDelegate;
import otus.highload.socialnetwork.openapi.model.User;
import otus.highload.socialnetwork.openapi.model.UserRegisterPost200Response;
import otus.highload.socialnetwork.openapi.model.UserRegisterPostRequest;

import java.util.List;
import java.util.Optional;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UserApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<User> userGetIdGet(String id) {
        return UserApiDelegate.super.userGetIdGet(id);
    }

    @Override
    public ResponseEntity<UserRegisterPost200Response> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        return UserApiDelegate.super.userRegisterPost(userRegisterPostRequest);
    }

    @Override
    public ResponseEntity<List<User>> userSearchGet(String firstName, String lastName) {
        return UserApiDelegate.super.userSearchGet(firstName, lastName);
    }

}
