package AOOPClassAssignment1;

public class Main {
    public static void main(String[] args) {
        vehicleCRUD vCRUD = new vehicleCRUD();

        // Creating some test vehicles
        Vehicle truck = new Truck(8000, "Red", 1, "Actros", "Mercedes Benz", 12800, "Saketh Kenchem", 50000, 3, 18, 30000, 25000);
        Vehicle truck2 = new Truck(8000, "Silver", 2, "TGX", "MAN", 10518, "Thomas the Cat", 25000, 3, 6, 23000, 15000);
        Vehicle motorcycle = new Motorcycle(200, "Black", 3, "Bullet", "Royal Enfield", 550, "Jerry the Mouse", 850, false, true, false);
        Vehicle motorcycle2 = new Motorcycle(185, "Green", 4, "Pulsar-N250", "Bajaj Motors", 250, "Iron Man", 600, false, true, true);
        Vehicle tractor = new Tractor(3000, "Green", 5, "6R 195", "John Deere", 4000, "Daemon Targaryen", 20000, true, true);
        

        // Inserting the test vehicles into database
        //vCRUD.insertOperation(truck);
        //vCRUD.insertOperation(motorcycle);
        //vCRUD.insertOperation(tractor);
        //vCRUD.insertOperation(truck2);
        //vCRUD.insertOperation(motorcycle2);

        // Select operation to display data
        vCRUD.selectOperation();

        //Update operation for vehicle data
        //vCRUD.updateOperation(2, "Johnny Storm", "Purple");
        //vCRUD.updateOperation(3, "Bran the Broken", "Royal Blue");
        
        vCRUD.selectOperation();
        
        // Delete operation for vehicle data
        vCRUD.deleteOperation(3);
        
        vCRUD.selectOperation();

    }
}
