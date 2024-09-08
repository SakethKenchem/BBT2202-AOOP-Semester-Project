package AOOPClassAssignment1;

public abstract class MotorVehicle implements Vehicle {
    private int weight;
    private String color;
    private final int mvID;
    private String model;
    private String make;
    private int engineCapacity;
    private String owner;
    private int mileage;

    public MotorVehicle(int weight, String color, int mvID, String model, String make, int engineCapacity, String owner, int mileage) {
        this.weight = weight;
        this.color = color;
        this.mvID = mvID;
        this.model = model;
        this.make = make;
        this.engineCapacity = engineCapacity;
        this.owner = owner;
        this.mileage = mileage;
    }

    @Override
    public void start() {
        System.out.println("Vehicle has started");
    }

    @Override
    public void repair() {
        System.out.println("Vehicle has been repaired");
    }

    @Override
    public void stopEngine() {
        System.out.println("Vehicle has stopped");
    }

    @Override
    public void displayDetails() {
        System.out.println("Vehicle Details:");
        System.out.println("Weight: " + weight);
        System.out.println("Color: " + color);
        System.out.println("Model: " + model);
        System.out.println("Make: " + make);
        System.out.println("Engine Capacity: " + engineCapacity + " cc");
        System.out.println("Owner: " + owner);
        System.out.println("Mileage: " + mileage + " km");
    }

    // Getters and Setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMvID() {
        return mvID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
