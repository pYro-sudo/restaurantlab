package by.losik.restaurant;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListOfOrders list= new ListOfOrders();
        Staff staff;
        List<GuestImpl> listOfGuests = new ArrayList<>();
        List<WaiterImpl> waiterList = new ArrayList<>();
        List<CookImpl> cookList = new ArrayList<>();
        List<ChiefImpl> chiefList = new ArrayList<>();
        List<ManagerCookImpl> managerCookList = new ArrayList<>();
        List<ManagerChiefImpl> managerChiefList = new ArrayList<>();
        List<ManagerWaiterImpl> managerWaiterList = new ArrayList<>();
        List<JanitorImpl> janitorList = new ArrayList<>();
        while(true){
            try{
                System.out.println("Enter the name");
                String name = new Scanner(System.in).nextLine();
                System.out.println("Enter the number of position or action\n0 - exit\n1 - chief\n2 - cook\n3 - guest\n4 - manager of chiefs\n" +
                        "5 - manager of cooks\n6 - manager of waiter\n7 - owner\n8 - waiter\n9 - janitor");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        System.out.println("Enter the salary");
                        double salary = new Scanner(System.in).nextDouble();
                        staff = new ChiefImpl(salary,Position.CHIEF,name);
                        chiefOperations((ChiefImpl) staff, list);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        chiefList.add((ChiefImpl) staff);
                        break;
                    case 2:
                        System.out.println("Enter the salary");
                        double salary1 = new Scanner(System.in).nextDouble();
                        staff = new CookImpl(salary1,Position.COOK,name);
                        cookOperations((CookImpl) staff, list);
                        cookList.add((CookImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 3:
                        GuestImpl guest = new GuestImpl(name);
                        guestOperations(guest);
                        listOfGuests.add(guest);
                        break;
                    case 4:
                        System.out.println("Enter the salary");
                        double salary2 = new Scanner(System.in).nextDouble();
                        staff = new ManagerChiefImpl(salary2,Position.MANAGER_CHIEF,name);
                        managerChiefOperations((ManagerChiefImpl) staff, chiefList);
                        managerChiefList.add((ManagerChiefImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 5:
                        System.out.println("Enter the salary");
                        double salary3 = new Scanner(System.in).nextDouble();
                        staff = new ManagerCookImpl(salary3,Position.MANAGER_COOK,name);
                        managerCookOperations((ManagerCookImpl)staff, cookList);
                        managerCookList.add((ManagerCookImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 6:
                        System.out.println("Enter the salary");
                        double salary4 = new Scanner(System.in).nextDouble();
                        staff = new ManagerWaiterImpl(salary4,Position.MANAGER_WAITER,name);
                        managerWaiterOperations((ManagerWaiterImpl)staff, waiterList);
                        managerWaiterList.add((ManagerWaiterImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 7:
                        System.out.println("Enter the salary");
                        double salary5 = new Scanner(System.in).nextDouble();
                        staff = new OwnerImpl(salary5,Position.OWNER,name);
                        ownerOperations((OwnerImpl) staff, managerChiefList, managerWaiterList, managerCookList, janitorList);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 8:
                        System.out.println("Enter the salary");
                        double salary6 = new Scanner(System.in).nextDouble();
                        staff = new WaiterImpl(salary6,Position.WAITER,name);
                        waiterOperations((WaiterImpl) staff, listOfGuests, list);
                        waiterList.add((WaiterImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    case 9:
                        System.out.println("Enter the salary");
                        double salary7 = new Scanner(System.in).nextDouble();
                        staff = new JanitorImpl(salary7,Position.JANITOR,name);
                        janitorOperations((JanitorImpl) staff);
                        janitorList.add((JanitorImpl) staff);
                        new Executor().insertStaffInfo(staff,new Executor().connectToEmployees());
                        break;
                    default:
                        System.out.println("could not comprehend");
                        break;
                }
            }
            catch (Exception e){
                System.out.println("The error is:");
                e.printStackTrace();
                return;
            }
        }

    }

    public static void chiefOperations(ChiefImpl staff, ListOfOrders list){
        try {
            if(list.getListOfOrders() == null){
                if(list.getListOfOrders().isEmpty()) {
                    System.out.println("There is nothing to do");
                    return;
                }
            }
            System.out.println("enter the number of operation\n0 - exit\n1 - take order\n2 - cook food\n3 - give order to cook");
            int choice = new Scanner(System.in).nextInt();
            switch (choice){
                case 0:
                    return;
                case 1:
                    staff.takeOrder(list);
                    break;
                case 2:
                    staff.cookFood(list);
                    break;
                case 3:
                    System.out.println("Enter the name of cook");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("enter the salary");
                    double salary = new Scanner(System.in).nextDouble();
                    staff.giveOrderToACook(new CookImpl(salary,Position.COOK,name),list);
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void cookOperations(CookImpl staff, ListOfOrders list){
        try {
            if(list.getListOfOrders() == null){
                if(list.getListOfOrders().isEmpty()) {
                    System.out.println("There is nothing to do");
                    return;
                }
            }
            System.out.println("enter the number of operation\n0 - exit\n1 - take order\n2 - cook food\n3 - give order to cook");
            int choice = new Scanner(System.in).nextInt();
            switch (choice){
                case 0:
                    return;
                case 1:
                    staff.takeOrder(list);
                    break;
                case 2:
                    staff.cookFood(list);
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void guestOperations(GuestImpl guest){
        System.out.println("Enter the number of operation\n0 - exit\n1 - change name\n2 - get name\n" +
                "3 - tip\n4 - check order\n5 - set order\n6 - write opinion\n7 - pay for the order");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 0:
                return;
            case 1:
                System.out.println("Enter your name");
                guest.setName(new Scanner(System.in).nextLine());
                break;
            case 2:
                System.out.println("The name is set is: "+guest.getName());
                break;
            case 3:
                System.out.println("enter the amount of tip");
                double amount = new Scanner(System.in).nextDouble();
                guest.tip(amount);
                break;
            case 4:
                System.out.println("Order: "+guest.getOrder().getNameOfOrder()+"\nYour name: "+guest.getOrder().getNameOfGuest()+"\n" +
                        "price: "+guest.getOrder().getPrice());
                break;
            case 5:
                System.out.println("The menu:\n"+new JSONObject("menu.json"));
                System.out.println("What would you order?\n");
                String orderName = new Scanner(System.in).nextLine();
                System.out.println("Enter the price");
                double price = new Scanner(System.in).nextDouble();
                guest.setOrder(orderName,price);
                break;
            case 6:
                System.out.println("Enter your opinion");
                guest.setOpinion(new Scanner(System.in).nextLine());
                guest.writeReview();
                break;
            case 7:
                guest.payForTheOrder(guest.getOrder());
                break;
            default:
                System.out.println("Could not comprehend");
                break;
        }
    }

    public static void janitorOperations(JanitorImpl staff){
        System.out.println("Enter the operation\n0 - exit\nclean rooms");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 0:
                return;
            case 1:
                staff.cleanRooms();
            default:
                System.out.println("could not comprehend");
                break;
        }
    }

    public static void managerChiefOperations(ManagerChiefImpl staff, List<ChiefImpl> list){
        try{
            while (true){
                System.out.println("Enter the operation\n0 - exit\n1 - hire staff\n2 - fire staff\n3 - promote staff" +
                        "\n4 - change salary\n5 - get staff by position\n6 - get staff by name");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        System.out.println("enter the salary");
                        double salary = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name = new Scanner(System.in).nextLine();
                        ChiefImpl chief = new ChiefImpl(salary,Position.CHIEF,name);
                        list.add(chief);
                        new Executor().insertStaffInfo(chief,new Executor().connectToEmployees());
                        break;
                    case 2:
                        System.out.println("enter the salary");
                        double salary1 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name1 = new Scanner(System.in).nextLine();
                        ChiefImpl chief1 = new ChiefImpl(salary1,Position.CHIEF,name1);
                        new Executor().deleteStaffInfo(chief1, new Executor().connectToEmployees());
                        for(ChiefImpl chief2 : list){
                            if(chief2.getName().equals(chief1.getName())){
                                list.remove(chief1);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("enter the salary");
                        double salary2 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name2 = new Scanner(System.in).nextLine();
                        ChiefImpl chief2 = new ChiefImpl(salary2,Position.CHIEF,name2);
                        for(ChiefImpl chief3 : list){
                            if(chief2.getName().equals(chief3.getName())){
                                staff.promoteEntity(chief3,Position.MANAGER_CHIEF,4000);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("enter the salary");
                        double salary3 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name3 = new Scanner(System.in).nextLine();
                        ChiefImpl chief3 = new ChiefImpl(salary3,Position.CHIEF,name3);
                        for(ChiefImpl chief4 : list){
                            if(chief4.getName().equals(chief3.getName())){
                                staff.changeSalary(chief4,2000);
                            }
                        }
                        break;
                    case 5:
                        for(Document document: staff.findStaffByPosition(Position.CHIEF)){
                            System.out.println(document.toString());
                        }
                    case 6:
                        System.out.println("enter the name");
                        String name4 = new Scanner(System.in).nextLine();
                        for(Document document: staff.findStaffByName(name4)){
                            System.out.println(document.toString());
                        }
                    default:
                        System.out.println("could not comprehend");
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void managerCookOperations(ManagerCookImpl staff, List<CookImpl> list){
        try{
            while (true){
                System.out.println("Enter the operation\n0 - exit\n1 - hire staff\n2 - fire staff\n3 - promote staff" +
                        "\n4 - change salary\n5 - get staff by position\n6 - get staff by name");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        System.out.println("enter the salary");
                        double salary = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name = new Scanner(System.in).nextLine();
                        CookImpl cook = new CookImpl(salary,Position.CHIEF,name);
                        list.add(cook);
                        new Executor().insertStaffInfo(cook,new Executor().connectToEmployees());
                        break;
                    case 2:
                        System.out.println("enter the salary");
                        double salary1 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name1 = new Scanner(System.in).nextLine();
                        CookImpl cook1 = new CookImpl(salary1,Position.CHIEF,name1);
                        new Executor().deleteStaffInfo(cook1, new Executor().connectToEmployees());
                        for(CookImpl cook2 : list){
                            if(cook2.getName().equals(cook1.getName())){
                                list.remove(cook1);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("enter the salary");
                        double salary2 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name2 = new Scanner(System.in).nextLine();
                        CookImpl cook2 = new CookImpl(salary2,Position.CHIEF,name2);
                        for(CookImpl cook3 : list){
                            if(cook2.getName().equals(cook3.getName())){
                                staff.promoteEntity(cook3,Position.MANAGER_CHIEF,4000);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("enter the salary");
                        double salary3 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name3 = new Scanner(System.in).nextLine();
                        CookImpl cook3 = new CookImpl(salary3,Position.CHIEF,name3);
                        for(CookImpl chief4 : list){
                            if(chief4.getName().equals(cook3.getName())){
                                staff.changeSalary(chief4,2000);
                            }
                        }
                        break;
                    case 5:
                        for(Document document: staff.findStaffByPosition(Position.COOK)){
                            System.out.println(document.toString());
                        }
                    case 6:
                        System.out.println("enter the name");
                        String name4 = new Scanner(System.in).nextLine();
                        for(Document document: staff.findStaffByName(name4)){
                            System.out.println(document.toString());
                        }
                    default:
                        System.out.println("could not comprehend");
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void managerWaiterOperations(ManagerWaiterImpl staff, List<WaiterImpl> list){
        try{
            while (true){
                System.out.println("Enter the operation\n0 - exit\n1 - hire staff\n2 - fire staff\n3 - promote staff" +
                        "\n4 - change salary\n5 - get staff by position\n6 - get staff by name");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        System.out.println("enter the salary");
                        double salary = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name = new Scanner(System.in).nextLine();
                        WaiterImpl waiter = new WaiterImpl(salary,Position.CHIEF,name);
                        list.add(waiter);
                        new Executor().insertStaffInfo(waiter,new Executor().connectToEmployees());
                        break;
                    case 2:
                        System.out.println("enter the salary");
                        double salary1 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name1 = new Scanner(System.in).nextLine();
                        WaiterImpl waiter1 = new WaiterImpl(salary1,Position.CHIEF,name1);
                        new Executor().deleteStaffInfo(waiter1, new Executor().connectToEmployees());
                        for(WaiterImpl waiter2 : list){
                            if(waiter2.getName().equals(waiter1.getName())){
                                list.remove(waiter1);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("enter the salary");
                        double salary2 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name2 = new Scanner(System.in).nextLine();
                        WaiterImpl waiter2 = new WaiterImpl(salary2,Position.CHIEF,name2);
                        for(WaiterImpl waiter3 : list){
                            if(waiter2.getName().equals(waiter3.getName())){
                                staff.promoteEntity(waiter3,Position.MANAGER_CHIEF,4000);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("enter the salary");
                        double salary3 = new Scanner(System.in).nextDouble();
                        System.out.println("Enter the name");
                        String name3 = new Scanner(System.in).nextLine();
                        WaiterImpl waiter4 = new WaiterImpl(salary3,Position.CHIEF,name3);
                        for(WaiterImpl waiter3 : list){
                            if(waiter3.getName().equals(waiter4.getName())){
                                staff.changeSalary(waiter3,2000);
                            }
                        }
                        break;
                    case 5:
                        for(Document document: staff.findStaffByPosition(Position.WAITER)){
                            System.out.println(document.toString());
                        }
                    case 6:
                        System.out.println("enter the name");
                        String name4 = new Scanner(System.in).nextLine();
                        for(Document document: staff.findStaffByName(name4)){
                            System.out.println(document.toString());
                        }
                    default:
                        System.out.println("could not comprehend");
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void ownerOperations(OwnerImpl staff, List<ManagerChiefImpl> managerChiefList, List<ManagerWaiterImpl> managerWaiterList,
                                       List<ManagerCookImpl> managerCookList, List<JanitorImpl> janitorList){
        try{
            while (true){
                System.out.println("Enter the operation\n0 - exit\n1 - hire staff\n2 - fire staff\n3 - get tips\n" +
                        "4 - find staff by name\n5 - get guests");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        System.out.println("enter the name of manager");
                        String name = new Scanner(System.in).nextLine();
                        System.out.println("Enter the salary");
                        double salary = new Scanner(System.in).nextDouble();
                        System.out.println("enter the position");
                        String position = new Scanner(System.in).nextLine();
                        switch (position){
                            case "cookManager":
                                managerCookList.add(new ManagerCookImpl(salary,Position.MANAGER_COOK,name));
                                new Executor().insertStaffInfo(new ManagerCookImpl(salary,Position.MANAGER_COOK,name), new Executor().connectToEmployees());
                                break;
                            case "chiefManager":
                                managerChiefList.add(new ManagerChiefImpl(salary,Position.MANAGER_COOK,name));
                                new Executor().insertStaffInfo(new ManagerChiefImpl(salary,Position.MANAGER_CHIEF,name), new Executor().connectToEmployees());
                                break;
                            case "waiterManager":
                                managerWaiterList.add(new ManagerWaiterImpl(salary,Position.MANAGER_COOK,name));
                                new Executor().insertStaffInfo(new ManagerWaiterImpl(salary,Position.MANAGER_WAITER,name), new Executor().connectToEmployees());
                                break;
                            case "janitor":
                                janitorList.add(new JanitorImpl(salary,Position.JANITOR,name));
                                new Executor().insertStaffInfo(new JanitorImpl(salary,Position.JANITOR,name), new Executor().connectToEmployees());
                                break;
                            default:
                                System.out.println("could nor comprehend");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("enter the name of manager");
                        String name1 = new Scanner(System.in).nextLine();
                        System.out.println("Enter the salary");
                        double salary1 = new Scanner(System.in).nextDouble();
                        System.out.println("enter the position");
                        String position1 = new Scanner(System.in).nextLine();
                        switch (position1){
                            case "cookManager":
                                for(ManagerCookImpl managerCook: managerCookList){
                                    if(managerCook.getName().equals(name1)){
                                        managerCookList.remove(managerCook);
                                    }
                                }
                                new Executor().deleteStaffInfo(new ManagerCookImpl(salary1,Position.MANAGER_COOK,name1), new Executor().connectToEmployees());
                                break;
                            case "chiefManager":
                                for(ManagerChiefImpl managerChief: managerChiefList){
                                    if(managerChief.getName().equals(name1)){
                                        managerCookList.remove(managerChief);
                                    }
                                }
                                new Executor().deleteStaffInfo(new ManagerChiefImpl(salary1,Position.MANAGER_CHIEF,name1), new Executor().connectToEmployees());
                                break;
                            case "waiterManager":
                                for(ManagerWaiterImpl managerWaiter: managerWaiterList){
                                    if(managerWaiter.getName().equals(name1)){
                                        managerCookList.remove(managerWaiter);
                                    }
                                }
                                new Executor().deleteStaffInfo(new ManagerCookImpl(salary1,Position.MANAGER_WAITER,name1), new Executor().connectToEmployees());
                                break;
                            case "janitor":
                                for(JanitorImpl janitor: janitorList){
                                    if(janitor.getName().equals(name1)){
                                        janitorList.remove(janitor);
                                    }
                                }
                                new Executor().deleteStaffInfo(new JanitorImpl(salary1, Position.JANITOR,name1), new Executor().connectToEmployees());
                            default:
                                System.out.println("could nor comprehend");
                                break;
                        }
                        break;
                    case 3:
                        for(Document document: new Executor().getTips(new Executor().connectToTip())){
                            System.out.println(document.toString());
                        }
                        break;
                    case 4:
                        System.out.println("Enter the name of staff");
                        new Executor().findStaffInfoByName(new Scanner(System.in).nextLine(),new Executor().connectToEmployees());
                        break;
                    case 5:
                        for(Document document: new Executor().getGuests(new Executor().connectToGuests())){
                            System.out.println(document.toString());
                        }
                        break;
                    default:
                        System.out.println("Could not comprehend");
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void waiterOperations(WaiterImpl staff, List<GuestImpl> guestList, ListOfOrders list){
        try {
            while(true){
                System.out.println("Enter the operation\n0 - exit\n1 - get order\n2 - add order to list\n3 - check the order");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        staff.getOrder(guestList.get((int)Math.random()*guestList.size()));
                        break;
                    case 2:
                        staff.addOrderToList(staff.getOrderSelf(),list);
                    case 3:
                        System.out.println("Order: "+staff.getOrderSelf().getNameOfOrder()+"\nName: "+staff.getOrderSelf().getNameOfGuest()+"\n" +
                                "price: "+staff.getOrderSelf().getPrice());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}