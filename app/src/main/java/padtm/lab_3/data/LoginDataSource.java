package padtm.lab_3.data;

import padtm.lab_3.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "gm32673@example.com:haslo1"};
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            for (String credential : DUMMY_CREDENTIALS)
            {
                String[] pieces = credential.split(":");
                if(pieces[0].equals(username))
                {
                    if (pieces[1].equals(password))
                    {
                        LoggedInUser dummyUser =
                                new LoggedInUser(
                                        java.util.UUID.randomUUID().toString(),
                                        pieces[0]);
                        return new Result.Success<>(dummyUser);
                    }
                }
            }
            return new Result.Error(new IOException("Username or password is incorrect."));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}