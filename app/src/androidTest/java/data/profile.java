package data;

public class profile {
    private double weight;
    private int length;
    private int age;
    private String gender;
    private int active;   //how much hes active in a day (1: low , 2:mid , 3: high)

    public profile(){

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "profile{" +
                "weight=" + weight +
                ", length=" + length +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", active=" + active +
                '}';
    }
}
