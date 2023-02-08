package unit1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {

    private String name;
    private List<OfficeSupply> officeSupplies;

    public Employee(String name) {
        this.name = name;
        officeSupplies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return officeSupplies;
    }

    public Employee addOfficeSupply(OfficeSupply officeSupply) {
        this.officeSupplies.add(officeSupply);
        return this;
    }

    public void setOfficeSupplies(List<OfficeSupply> officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        if (!Objects.equals(name, employee.name)) {
            return false;
        }
        return Objects.equals(officeSupplies, employee.officeSupplies);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (officeSupplies != null ? officeSupplies.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "name='" + name + '\'' +
            ", officeSupplies=" + officeSupplies +
            '}';
    }
}
