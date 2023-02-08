package unit1.services;

import unit1.dto.Employee;

public class CostSummaryService {

    public double getCostSummaryByEmployee(Employee employee){
        var officeSupplies = employee.getOfficeSupplies();
        return officeSupplies.stream().mapToDouble(e->e.getCost()).sum();
    }

}
