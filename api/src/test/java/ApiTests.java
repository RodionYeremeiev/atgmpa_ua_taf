import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ApiTests {

    @Test
    @DisplayName("API test Stub")
    public void test(){
        log.info("API TEST MESSAGE");
        Assertions.assertEquals(1,1, "TestStub");
    }
}
