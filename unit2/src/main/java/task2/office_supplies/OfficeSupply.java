package task2.office_supplies;

import java.util.Objects;

public abstract class OfficeSupply {

    protected String name;
    protected double cost;

    public OfficeSupply(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfficeSupply that = (OfficeSupply) o;

        if (Double.compare(that.cost, cost) != 0) {
            return false;
        }
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OfficeSupply{" +
            "name='" + name + '\'' +
            ", cost=" + cost +
            '}';
    }
}
