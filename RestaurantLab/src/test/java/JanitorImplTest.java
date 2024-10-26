import org.junit.Assert;
import org.junit.Test;
import by.losik.restaurant.JanitorImpl;
import by.losik.restaurant.Position;

public class JanitorImplTest {
    @Test
    public void testJanitorImpl(){
        Assert.assertEquals(new JanitorImpl(12, Position.JANITOR, "sdf").cleanRooms(),"All rooms are clean");
    }
}
