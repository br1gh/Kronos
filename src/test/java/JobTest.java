import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class JobTest
{
    @Test
    @DisplayName("dateAllNull")
    public void dateAllNull()
    {
        Job j = new Job(0, "", null, null, null, null, null);
        assertTrue(j.doesDateSatisfy(LocalDateTime.now()));
    }

    @Test
    @DisplayName("dateExact")
    public void dateExact()
    {
        Job j = new Job(0, "", 1, 1, 6, 1, 1);
        assertTrue(j.doesDateSatisfy(LocalDateTime.of(2022, 1, 1, 1, 1)));
    }

}
