package otus.highload.socialnetwork.delegates;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import otus.highload.socialnetwork.openapi.api.UserApiDelegate;
import otus.highload.socialnetwork.openapi.model.User;
import otus.highload.socialnetwork.openapi.model.UserRegisterPost200Response;
import otus.highload.socialnetwork.openapi.model.UserRegisterPostRequest;
import otus.highload.socialnetwork.service.CreateUserService;
import otus.highload.socialnetwork.service.GetUserService;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UserApiDelegate {

    @Autowired
    private final CreateUserService createUserService;
    @Autowired
    private final GetUserService getUserService;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UserApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<User> userGetIdGet(String id) {
        User user = getUserService.get(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserRegisterPost200Response> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        UserRegisterPost200Response registeredUserId = createUserService.register(userRegisterPostRequest);
        return ResponseEntity.ok(registeredUserId);
    }

    @Override
    public ResponseEntity<List<User>> userSearchGet(String firstName, String lastName) {
        List<User> users = getUserService.useSearch(firstName, lastName);
        return ResponseEntity.ok(users);
    }

}
