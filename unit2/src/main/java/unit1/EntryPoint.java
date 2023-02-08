package unit1;

import unit1.dto.Employee;
import unit1.dto.OfficeSupply;
import unit1.services.CostSummaryService;

public class EntryPoint {

    public static void main(String[] args) {
        CostSummaryService costSummaryService = new CostSummaryService();

        Employee employee = new Employee("Andrey");
        employee.addOfficeSupply(new OfficeSupply("scissors",15.0))
            .addOfficeSupply(new OfficeSupply("pen",2.5))
            .addOfficeSupply(new OfficeSupply("notepad",10));

        double cost = costSummaryService.getCostSummaryByEmployee(employee);
        System.out.printf("Cost of %s's office supplies is:%.2f",employee.getName(),cost);
    }
}
