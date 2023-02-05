package rao.rohi.rohirealfinalproject.data;

public class StepsProfile {
    private int Steps;
    private double Distance;
    private int CalorieBurn;
    private int CalorieEat;
    private String owner;
    private String Key;

    public StepsProfile(){

    }

    public int getSteps() {
        return Steps;
    }

    public String getKey(){
        return Key;
    }

    public double getDistance() {
        return Distance;
    }

    public int getCalorieBurn() {
        return CalorieBurn;
    }

    public int getCalorieEat() {
        return CalorieEat;
    }

    public String getOwner() {
        return owner;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public void setCalorieBurn(int calorieBurn) {
        CalorieBurn = calorieBurn;
    }

    public void setCalorieEat(int calorieEat) {
        CalorieEat = calorieEat;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setKey(String key){
        this.Key=key;
    }

    @Override
    public String toString() {
        return "StepsProfile{" +
                "Steps=" + Steps +
                ", Distance=" + Distance +
                ", CalorieBurn=" + CalorieBurn +
                ", CalorieEat=" + CalorieEat +
                ", owner='" + owner + '\'' +
                ", Key='" + Key + '\'' +
                '}';
    }
}
