package by.losik.restaurant;

import com.mongodb.client.FindIterable;
import org.bson.Document;

public class ManagerWaiterImpl extends Staff {
    public ManagerWaiterImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    public Staff hireStaff(WaiterImpl entity){
        return new Executor().insertStaffInfo(entity, new Executor().connectToEmployees());
    }

    public Staff fireStaff(WaiterImpl entity){
        return new Executor().deleteStaffInfo(entity, new Executor().connectToEmployees());
    }

    public Staff promoteEntity(WaiterImpl entity, Position position, double salary){
        return new Executor().updateStaffByNameAndPromote(entity.getName(), position, salary, new Executor().connectToEmployees());
    }

    public Staff changeSalary(WaiterImpl entity, double salary){
        return new Executor().updateStaffSalaryByName(entity.getName(), entity.getPosition(), salary, new Executor().connectToEmployees());
    }

    public FindIterable<Document> findStaffByPosition(Position position){
        return new Executor().findStaffByPosition(position, new Executor().connectToEmployees());
    }

    public FindIterable<Document> findStaffByName(String name){
        return new Executor().findStaffInfoByName(name,new Executor().connectToEmployees());
    }
}
