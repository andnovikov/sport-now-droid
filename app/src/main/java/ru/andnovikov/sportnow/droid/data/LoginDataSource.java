package ru.andnovikov.sportnow.droid.data;

import android.os.AsyncTask;
import android.util.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.andnovikov.sportnow.droid.data.model.LoggedInUser;
import ru.andnovikov.sportnow.droid.data.model.dto.LoginDto;
import ru.andnovikov.sportnow.droid.data.model.dto.UserDto;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            UserDto user = new HttpRequestTask().execute(username, password).get();
            LoggedInUser loggedInUser = new LoggedInUser(user.getId(), user.getFirstName() + " " + user.getLastName());
            return new Result.Success<>(loggedInUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private class HttpRequestTask extends AsyncTask<String, Void, UserDto> {
        @Override
        protected UserDto doInBackground(String... params) {
            try {
                final String url = "https://sport-now-ru.herokuapp.com/api/user/authenticate";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                LoginDto login = new LoginDto(params[0], params[1]);
                UserDto userDto = restTemplate.postForObject(url, login, UserDto.class);

                return userDto;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(UserDto userDto) {
            // user = userDto;
        }

    }
}
