import by.losik.restaurant.*;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ManagerCookTest {
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
    public void testManagerCookImpl(){
        Assert.assertEquals(managerCook.hireStaff(cook),cook);
        Assert.assertEquals(managerCook.fireStaff(cook),cook);
        Assert.assertEquals(managerCook.promoteEntity(cook,Position.COOK, cook.getSalary()).getPosition(),Position.COOK);
        Assert.assertEquals(managerCook.promoteEntity(cook,Position.COOK, cook.getSalary()).getSalary(),1500,0);
        Assert.assertEquals(managerCook.promoteEntity(cook,Position.COOK, cook.getSalary()).getName(),"NameOfCook");
        Assert.assertEquals(managerCook.changeSalary(cook,2000).getName(),"NameOfCook");
        Assert.assertEquals(managerCook.changeSalary(cook,1800).getSalary(),1800,0);
        Assert.assertEquals(managerCook.changeSalary(cook,200).getPosition(),Position.COOK);
        Assert.assertTrue(managerCook.findStaffByPosition(Position.COOK) instanceof FindIterable<Document>);
        assertThat(managerCook.findStaffByName("name"),instanceOf(FindIterable.class));
    }
}
