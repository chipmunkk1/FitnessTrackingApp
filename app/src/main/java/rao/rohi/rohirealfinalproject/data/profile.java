package rao.rohi.rohirealfinalproject.data;

/**
 * profile
 */

public class profile {
    private double weight;
    private String key;
    private String owner;
    private int length;
    private int age;
    private boolean gender;
    private int active;   //how much hes active in a day (1: low , 2:mid , 3: high)

    public profile(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }



    //bulking formula for females and males
    public double BulkingCalories(){
        if(this.getGender()==true){
            double BMRForMen= 66.47+(13.75*this.getWeight())+5.003*this.getLength()-(6.755*this.getAge());
            if(this.getActive()<=2){
                return 1.2*BMRForMen;
            }
            else if(this.getActive()>2 && this.getActive()<=5){
                return BMRForMen*1.55;
            }
            return BMRForMen*1.725;
        }
        double BMRForWomen= 655.1+(9.563*this.getWeight())+1.850*this.getLength()-(6.755*this.getAge());
        if(this.getActive()<=2){
            return 1.2*BMRForWomen;
        }
        else if(this.getActive()>2 &&this.getActive()<=5){
            return BMRForWomen*1.55;
        }
        return BMRForWomen*1.725;

    }


    //
    //Cutting formula for Females and males
    public double CuttingCalories(){
        if(this.getGender()==true){
            return 10*this.getWeight()+6.25*this.getLength()-5*this.getAge()+5;
        }
        return 10*this.getWeight()+6.25*this.getLength()-5*this.getAge()-161;
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
