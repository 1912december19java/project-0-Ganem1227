import org.junit.Before;
import org.junit.Test;
import com.revature.repository.DatabaseManager;
import com.revature.service.Service;
import junit.framework.TestCase;

public class TestDriver extends TestCase{
  private DatabaseManager dbManager = new DatabaseManager(
      System.getenv("AWS_URL"),
      System.getenv("AWS_USERNAME"),
      System.getenv("AWS_PASSWORD")
      );
  
  
  public TestDriver(String testname) {
    super(testname);
  }
  
  @Before
  public void setup() {
  }
  
  
  @Test
  public void testVerifyPassword()
  {
      assertTrue(dbManager.verifyPassword("test_username", "test_password"));
  }
  
  @Test
  public void testGetBalanceFromDB() {
    assertEquals(dbManager.getBalanceFromDatabase("revature"), 0.0);
  }
}
