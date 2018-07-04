package api2.TOST;

import api2.service.Helper;
import api2.service.enums.Server;
import api2.service.enums.UserType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ForTesting {

    @Test
    void one() throws IOException {
        new Helper().getApiKey(UserType.CORPORATE, "NPIOS", "Hahl0Doo", Server.TEST);
    }
}
