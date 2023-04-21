package unit6.task3;

import java.util.Objects;

public class PhoneNumber {

    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    private String number;
    private PhoneType type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(number, that.number) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
               "number='" + number + '\'' +
               ", type=" + type +
               '}';
    }
}
