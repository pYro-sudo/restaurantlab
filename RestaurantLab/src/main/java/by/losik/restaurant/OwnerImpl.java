package by.losik.restaurant;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class OwnerImpl extends Staff {
    private List<ManagerChiefImpl> managerChiefList = new ArrayList<>();
    private List<ManagerCookImpl> managerCookList = new ArrayList<>();
    private List<ManagerWaiterImpl> managerWaiterList = new ArrayList<>();
    private List<Janitor> janitors = new ArrayList<>();

    public OwnerImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    public List<ManagerChiefImpl> getManagerChiefList() {
        return this.managerChiefList;
    }

    public void setManagerChiefList(List<ManagerChiefImpl> managerChiefList) {
        this.managerChiefList = managerChiefList;
    }

    public List<ManagerCookImpl> getManagerCookList() {
        return this.managerCookList;
    }

    public void setManagerCookList(List<ManagerCookImpl> managerCookList) {
        this.managerCookList = managerCookList;
    }

    public List<ManagerWaiterImpl> getManagerWaiterList() {
        return this.managerWaiterList;
    }

    public void setManagerWaiterList(List<ManagerWaiterImpl> managerWaiterList) {
        this.managerWaiterList = managerWaiterList;
    }

    public List<Janitor> getJanitors() {
        return this.janitors;
    }

    public void setJanitors(List<Janitor> janitors) {
        this.janitors = janitors;
    }

    public Object fireManager(Object object){
        if(object instanceof ManagerWaiterImpl){
            this.managerWaiterList.remove(object);
        }
        if(object instanceof ManagerCookImpl){
            this.managerCookList.remove(object);
        }
        if(object instanceof ManagerChiefImpl){
            this.managerChiefList.remove(object);
        }
        if(object instanceof JanitorImpl){
            this.janitors.remove(object);
        }
        return object;
    }

    public Object addManager(Object object){
        if(object instanceof ManagerWaiterImpl){
            this.managerWaiterList.add((ManagerWaiterImpl) object);
        }
        if(object instanceof ManagerCookImpl){
            this.managerCookList.add((ManagerCookImpl) object);
        }
        if(object instanceof ManagerChiefImpl){
            this.managerChiefList.add((ManagerChiefImpl) object);
        }
        if(object instanceof JanitorImpl){
            this.janitors.add((Janitor) object);
        }
        return object;
    }

    public FindIterable<Document> getTips(){
        return new Executor().getTips(new Executor().connectToTip());
    }

    public void insertManager(){
        for(ManagerChiefImpl managerChief: managerChiefList){
            new Executor().insertStaffInfo((Staff) managerChief,new Executor().connectToEmployees());
        }
        for(ManagerWaiterImpl managerWaiter: managerWaiterList){
            new Executor().insertStaffInfo((Staff) managerWaiter,new Executor().connectToEmployees());
        }
        for(ManagerCookImpl managerCook: managerCookList){
            new Executor().insertStaffInfo((Staff) managerCook,new Executor().connectToEmployees());
        }
        for(Janitor janitor: janitors){
            new Executor().insertStaffInfo((Staff) janitor,new Executor().connectToEmployees());
        }
    }

    public FindIterable<Document> findStaffByName(String name){
        return new Executor().findStaffInfoByName(name,new Executor().connectToEmployees());
    }

    public FindIterable<Document> findGuests(){
        return new Executor().getGuests(new Executor().connectToGuests());
    }
}
