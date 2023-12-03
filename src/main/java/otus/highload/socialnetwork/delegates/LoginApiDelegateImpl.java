package otus.highload.socialnetwork.delegates;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import otus.highload.socialnetwork.openapi.api.LoginApiDelegate;
import otus.highload.socialnetwork.openapi.model.LoginPost200Response;
import otus.highload.socialnetwork.openapi.model.LoginPostRequest;

import java.util.Optional;

@Component
public class LoginApiDelegateImpl implements LoginApiDelegate {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LoginApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {
        return LoginApiDelegate.super.loginPost(loginPostRequest);
    }

}
