import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Menu_AbstractTest extends Menu_Abstract{


    @Test
    void get_Receipt_Format() {

    assertEquals("Name - $10.0 - Q. 5",Get_Receipt_Format("Name", 10.0f, 5) );

    }

}