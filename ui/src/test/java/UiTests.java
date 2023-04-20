import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class UiTests {

    @Test
    @DisplayName("UI test Stub")
    public void test(){
        log.info("UI TEST MESSAGE");
        Assertions.assertEquals(1,1, "TestStub");
    }
}
