package unit2.task1.dto;

import java.util.Objects;

public class OfficeSupply {

    private String name;
    private double cost;

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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCost(long cost) {
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
        int nameHashCode = name != null ? name.hashCode() : 0;
        long temp = Double.doubleToLongBits(cost);
        return 31 * nameHashCode + (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return String.format("OfficeSupply{name='%s', cost=%s}",name,cost);
    }
}
