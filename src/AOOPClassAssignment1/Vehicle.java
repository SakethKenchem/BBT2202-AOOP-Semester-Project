package AOOPClassAssignment1;

import java.io.Serializable;

public interface Vehicle extends Serializable {
    void start();
    void repair();
    void stopEngine();
    void displayDetails();

    public int getMvID();

    public int getWeight();

    public String getColor();

    public String getModel();

    public String getMake();

    public int getEngineCapacity();

    public String getOwner();

    public int getMileage();

    public void setWeight(int parseInt);

    public void setColor(String text);

    public void setModel(String text);

    public void setMake(String text);

    public void setEngineCapacity(int parseInt);

    public void setOwner(String text);

    public void setMileage(int parseInt);
}