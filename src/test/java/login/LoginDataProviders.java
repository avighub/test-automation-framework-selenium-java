package login;

import org.testng.annotations.DataProvider;

public class LoginDataProviders {
  private LoginDataProviders()
    {
    }

  @DataProvider(parallel = false)
  public static Object[][] getValidLoginCredentials()
    {
      return new Object[][]{
              {"standard_user", "secret_sauce"},
              {"performance_glitch_user", "secret_sauce"},
      };
    }

  @DataProvider(parallel = false)
  public static Object[][] getInvalidLoginCredentials()
    {
      return new Object[][]{
              {"standard_user", "secret_sauce_invalid1"},
              {"performance_glitch_user", "secret_sauce_invalid2"},
              {"performance_glitch_user2", "secret_sauce_invalid3"},
      };
    }

}
