import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class JobTest
{
    @Test
    @DisplayName("dateAllNull")
    public void dateAllNull()
    {
        Job j = new Job(0, "", null, null, null, null);
        assertTrue(j.doesDateSatisfy());
    }

}
