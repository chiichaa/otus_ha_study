package otus.highload.socialnetwork.delegates;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import otus.highload.socialnetwork.openapi.api.LoginApiDelegate;
import otus.highload.socialnetwork.openapi.model.LoginPost200Response;
import otus.highload.socialnetwork.openapi.model.LoginPostRequest;
import otus.highload.socialnetwork.service.LoginUserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginApiDelegateImpl implements LoginApiDelegate {

    @Autowired
    private final LoginUserService loginUserService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LoginApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {
        LoginPost200Response userToken = loginUserService.getUserToken(loginPostRequest);
        return ResponseEntity.ok(userToken);
    }

}
