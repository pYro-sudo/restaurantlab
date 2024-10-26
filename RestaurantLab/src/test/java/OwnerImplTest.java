import by.losik.restaurant.*;
import com.mongodb.client.FindIterable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class OwnerImplTest {
    ChiefImpl chief;
    CookImpl cook;
    Executor executor;
    GuestImpl guest;
    JanitorImpl janitor;
    ListOfOrders list;
    ManagerWaiterImpl managerWaiter;
    ManagerChiefImpl managerChief;
    ManagerCookImpl managerCook;
    Order order;
    OwnerImpl owner;
    Review review;
    ReviewRepo reviewRepo;
    Staff staff;
    Tip tip;
    WaiterImpl waiter;
    @Before
    public void mockData(){
        chief = new ChiefImpl(3000,Position.CHIEF,"NameOfChief");
        cook = new CookImpl(1500,Position.COOK,"NameOfCook");
        executor = new Executor();
        guest = new GuestImpl("NameOfGuest");
        janitor = new JanitorImpl(1200,Position.JANITOR,"NameOfJanitor");
        list = new ListOfOrders();
        managerWaiter = new ManagerWaiterImpl(3500,Position.MANAGER_WAITER,"NameOfWaiterManager");
        managerChief = new ManagerChiefImpl(3750,Position.MANAGER_CHIEF,"NameOfChiefManager");
        managerCook = new ManagerCookImpl(3600,Position.MANAGER_COOK,"NameOfCookManager");
        order = new Order("Mashed Potatoes with beef and apple juice","NameOfGuest",80);
        owner = new OwnerImpl(5000,Position.OWNER,"NameOfOwner");
        review = new Review("Nah","NameOfGuest",3);
        reviewRepo = new ReviewRepo();
        staff = new Staff(0,Position.NONE,"NameOfStaff");
        tip = new Tip(10,"NameOfGuest");
        waiter = new WaiterImpl(1400,Position.WAITER,"NameOfWaiter");
    }

    @Test
    public void testOwnerImpl(){
        assertThat(owner.findGuests(),instanceOf(FindIterable.class));
        assertThat(owner.findStaffByName("NameOfStaff"),instanceOf(FindIterable.class));
        assertThat(owner.getTips(),instanceOf(FindIterable.class));
        Assert.assertEquals(owner.getManagerChiefList().isEmpty(),true);
        Assert.assertEquals(owner.addManager(managerChief),managerChief);
        Assert.assertEquals(owner.getManagerChiefList().isEmpty(),false);
        Assert.assertEquals(owner.getManagerCookList().isEmpty(),true);
        Assert.assertEquals(owner.addManager(managerCook),managerCook);
        Assert.assertEquals(owner.getManagerCookList().isEmpty(),false);
        Assert.assertEquals(owner.getManagerWaiterList().isEmpty(),true);
        Assert.assertEquals(owner.addManager(managerWaiter),managerWaiter);
        Assert.assertEquals(owner.getManagerWaiterList().isEmpty(),false);
        Assert.assertEquals(owner.fireManager(managerWaiter).equals(managerWaiter) && owner.getManagerWaiterList().isEmpty(), true);
        Assert.assertEquals(owner.fireManager(managerCook).equals(managerCook) && owner.getManagerCookList().isEmpty(), true);
        Assert.assertEquals(owner.fireManager(managerChief).equals(managerChief) && owner.getManagerChiefList().isEmpty(), true);
        owner.getJanitors().add(janitor);
        Assert.assertEquals(owner.getJanitors().isEmpty(), false);
        owner.setJanitors(new ArrayList<>());
        Assert.assertEquals(owner.getJanitors().isEmpty(), true);
        owner.setManagerChiefList(new ArrayList<>());
        Assert.assertEquals(owner.getManagerChiefList().isEmpty(),true);
        owner.setManagerCookList(new ArrayList<>());
        Assert.assertEquals(owner.getManagerCookList().isEmpty(),true);
        owner.setManagerWaiterList(new ArrayList<>());
        Assert.assertTrue(owner.getManagerWaiterList().isEmpty());
    }
}
