import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restaurant.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ApplicationTest {
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
    public void testChiefImpl(){
        Assert.assertEquals(chief.takeOrder(list),null);
        list.getListOfOrders().add(order);
        Assert.assertEquals(chief.takeOrder(list),order);
        Assert.assertEquals(chief.cookFood(list),order);
        Assert.assertEquals(chief.giveOrderToACook(cook,list),order);
    }

    @Test
    public void testCookImpl(){
        list.getListOfOrders().add(order);
        Assert.assertEquals(cook.cookFood(list),order);
        Assert.assertEquals(cook.takeOrder(list),order);
    }

    @Test
    public void testGuestImpl(){
        Assert.assertEquals(guest.getName(),"NameOfGuest");
        assertThat(guest.writeReview(),instanceOf(Review.class));
        assertThat(guest.tip(10),instanceOf(Tip.class));
        Assert.assertEquals(guest.payForTheOrder(order),order);

        GuestImpl guest1 = new GuestImpl("Name");
        guest1.setOrder("sd",1000.0);
        assertThat(guest1.getOrder(),instanceOf(Order.class));
        guest1.setOpinion("Bruh");
        Assert.assertEquals(guest1.getOpinion(),"Bruh");
        guest1.setName("Dude");
        Assert.assertEquals(guest1.getName(),"Dude");
    }

    @Test
    public void testJanitorImpl(){
        Assert.assertEquals(janitor.cleanRooms(),"All rooms are clean");
    }

    @Test
    public void testListOfOrders(){
        assertThat(list.getListOfOrders(),instanceOf(List.class));
        assertThat(list.getListOfReadyOrders(),instanceOf(List.class));
        Assert.assertTrue(list.getListOfOrders().isEmpty());
        Assert.assertTrue(list.getListOfReadyOrders().isEmpty());
        list.getListOfOrders().add(order);
        list.getListOfReadyOrders().add(order);
        Assert.assertEquals(list.getListOfOrders().get(0),order);
        Assert.assertEquals(list.getListOfReadyOrders().get(0),order);
        Assert.assertEquals(list.deleteOrderByName("Name"),"Name");
        Assert.assertEquals(list.deleteOrderByNameOfOrder("Name"),"Name");
        Assert.assertEquals(list.deleteOrderByPrice(10),10,0);
        assertThat(list.findOrderByNameOfGuest("Name"),instanceOf(FindIterable.class));
        assertThat(list.findOrderByNameOfOrder("Name"),instanceOf(FindIterable.class));
    }

    @Test
    public void testManagerChiefImpl(){
        Assert.assertEquals(managerChief.hireStaff(chief),chief);
        Assert.assertEquals(managerChief.fireStaff(chief),chief);
        Assert.assertEquals(managerChief.promoteEntity(chief,Position.CHIEF, chief.getSalary()).getPosition(),Position.CHIEF);
        Assert.assertEquals(managerChief.promoteEntity(chief,Position.CHIEF, chief.getSalary()).getSalary(),3000,0);
        Assert.assertEquals(managerChief.promoteEntity(chief,Position.CHIEF, chief.getSalary()).getName(),"NameOfChief");
        Assert.assertEquals(managerChief.changeSalary(chief,2000).getName(),"NameOfChief");
        Assert.assertEquals(managerChief.changeSalary(chief,6000).getSalary(),6000,0);
        Assert.assertEquals(managerChief.changeSalary(chief,2000).getPosition(),Position.CHIEF);
        Assert.assertTrue(managerChief.findStaffByPosition(Position.CHIEF) instanceof FindIterable<Document>);
        assertThat(managerChief.findStaffByName("name"),instanceOf(FindIterable.class));
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

    @Test
    public void testManagerWaiterImpl(){
        Assert.assertEquals(managerWaiter.hireStaff(waiter),waiter);
        Assert.assertEquals(managerWaiter.fireStaff(waiter),waiter);
        Assert.assertEquals(managerWaiter.promoteEntity(waiter,Position.WAITER, waiter.getSalary()).getPosition(),Position.WAITER);
        Assert.assertEquals(managerWaiter.promoteEntity(waiter,Position.WAITER, waiter.getSalary()).getSalary(),1400,0);
        Assert.assertEquals(managerWaiter.promoteEntity(waiter,Position.WAITER, waiter.getSalary()).getName(),"NameOfWaiter");
        Assert.assertEquals(managerWaiter.changeSalary(waiter,2000).getName(),"NameOfWaiter");
        Assert.assertEquals(managerWaiter.changeSalary(waiter,2000).getSalary(),2000,0);
        Assert.assertEquals(managerWaiter.changeSalary(waiter,2000).getPosition(),Position.WAITER);
        Assert.assertTrue(managerWaiter.findStaffByPosition(Position.WAITER) instanceof FindIterable<Document>);
        assertThat(managerWaiter.findStaffByName("name"),instanceOf(FindIterable.class));
    }

    @Test
    public void testOrder(){
        order.setNameOfOrder("Beer");
        Assert.assertEquals(order.getNameOfOrder(),"Beer");
        order.setNameOfGuest("Dude");
        Assert.assertEquals(order.getNameOfGuest(),"Dude");
        order.setPrice(0);
        Assert.assertEquals(order.getPrice(),0,0);
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

    @Test
    public void testReview(){
        review.setReview("no");
        Assert.assertEquals(review.getReview(),"no");
        review.setNameOfTheReviewer("name");
        Assert.assertEquals(review.getNameOfTheReviewer(),"name");
        review.setRating(7);
        Assert.assertEquals(review.getRating(),7,0);
    }

    @Test
    public void testReviewRepo(){
        reviewRepo.setReviewList();
        Assert.assertTrue(reviewRepo.getReviewList() instanceof FindIterable<Document>);
        Assert.assertTrue(reviewRepo.findReviewByRating(7) instanceof FindIterable<Document>);
        Assert.assertTrue(reviewRepo.findReviewByNameOfGuest("name") instanceof FindIterable<Document>);
    }

    @Test
    public void testWaiterImpl(){
        Assert.assertEquals(waiter.getOrder(guest),guest.getOrder());
        waiter.addOrderToList(order,list);
        Assert.assertEquals(waiter.getOrderSelf().getPrice(),80,0);
        Assert.assertEquals(waiter.getOrderSelf().getNameOfOrder(),"Mashed Potatoes with beef and apple juice");
        Assert.assertEquals(waiter.getOrderSelf().getNameOfGuest(),guest.getName());
        Assert.assertTrue(!list.getListOfOrders().isEmpty());
    }
}
