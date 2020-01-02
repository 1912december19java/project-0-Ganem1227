import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import com.revature.repository.DatabaseManager;
import com.revature.repository.UserSession;
import junit.framework.TestCase;

public class TestDriver extends TestCase{
  private DatabaseManager dbManager = new DatabaseManager(
      System.getenv("AWS_URL"),
      System.getenv("AWS_USERNAME"),
      System.getenv("AWS_PASSWORD")
      );
  
  @Before
  public void setup() {
  }
  
  
  @Test
  public void testVerifyPassword()
  {
      assertFalse(dbManager.verifyPassword("KurtCobain", "allapologies"));
      
      
      UserSession newProfile = new UserSession();
      newProfile.setBalance(0.0);
      newProfile.setUsername("KurtCobain");
      newProfile.setEmail("inUtero@subpop.com");
      newProfile.setPassword("allapologies");
      dbManager.newProfile(newProfile);
      
      assertTrue(dbManager.verifyPassword("KurtCobain", "allapologies"));
      assertFalse(dbManager.verifyPassword("KurtCobain", "walkingTheCow"));
      
      dbManager.removeProfile("KurtCobain");
      
      assertFalse(dbManager.verifyPassword("KurtCobain", "allapologies"));
  }
  
  @Test
  public void testGetBalanceFromDB() {
    assertThat(dbManager.getBalanceFromDatabase("revature"), is(0.0));
    assertThat(dbManager.getBalanceFromDatabase("Chopin"), not(0.0));
  }
}