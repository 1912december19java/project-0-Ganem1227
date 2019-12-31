import org.junit.Test;
import com.revature.service.Service;
import junit.framework.TestCase;

public class TestDriver extends TestCase{
  
  public TestDriver(String testname) {
    super(testname);
  }
  
  @Test
  public void testCheckUsername()
  {
      assertTrue(Service.checkUsername("test_username"));
  }
}
