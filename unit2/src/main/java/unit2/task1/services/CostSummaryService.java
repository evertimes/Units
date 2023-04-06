package unit2.task1.services;

import unit2.task1.dto.Employee;

public class CostSummaryService {

    public double getCostSummaryByEmployee(Employee employee){
        var officeSupplies = employee.getOfficeSupplies();
        return officeSupplies.stream().mapToDouble(e->e.getCost()).sum();
    }

}
