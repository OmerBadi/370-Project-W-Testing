import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lunch_MenuTest extends Menu_Abstract{

    @Test
    void show_Lunch()
    {
        ShowMenu("Lunch_Menu.txt");
    }
}