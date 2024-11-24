package AOOPClassAssignment1;

public class Main {
    public static void main(String[] args) {
        vehicleCRUD vCRUD = new vehicleCRUD();

        // Insert Trucks
        Vehicle truck1 = new Truck(8000, "Red", 1, "Actros", "Mercedes Benz", 12800, "Saketh Kenchem", 50000, 3, 18, 30000, 25000);
        Vehicle truck2 = new Truck(8000, "Silver", 2, "TGX", "MAN", 10518, "Thomas the Cat", 25000, 3, 6, 23000, 15000);
        Vehicle truck3 = new Truck(7500, "Blue", 6, "Volvo FH", "Volvo", 13000, "Alice Johnson", 30000, 2, 6, 25000, 20000);

        vCRUD.insertOperation(truck1);
        vCRUD.insertOperation(truck2);
        vCRUD.insertOperation(truck3);

        // Insert Motorcycles
        Vehicle motorcycle1 = new Motorcycle(200, "Black", 3, "Bullet", "Royal Enfield", 550, "Jerry the Mouse", 850, false, true, false);
        Vehicle motorcycle2 = new Motorcycle(185, "Green", 4, "Pulsar-N250", "Bajaj Motors", 250, "Iron Man", 600, false, true, true);
        Vehicle motorcycle3 = new Motorcycle(220, "Red", 7, "Ninja", "Kawasaki", 650, "Bob Smith", 1200, true, true, false);

        vCRUD.insertOperation(motorcycle1);
        vCRUD.insertOperation(motorcycle2);
        vCRUD.insertOperation(motorcycle3);

        // Insert Tractors
        Vehicle tractor1 = new Tractor(3000, "Green", 5, "6R 195", "John Deere", 4000, "Daemon Targaryen", 20000, true, true);
        Vehicle tractor2 = new Tractor(2800, "Blue", 8, "5075E", "John Deere", 3500, "Charlie Brown", 15000, false, true);

        vCRUD.insertOperation(tractor1);
        vCRUD.insertOperation(tractor2);

        // Display all inserted data
        System.out.println("All vehicles after insertion:");
        vCRUD.selectOperation();

        // Example of updating a vehicle
        System.out.println("\nUpdating vehicle with ID 2:");
        vCRUD.updateOperation(2, "Updated Owner", "Yellow");

        // Example of deleting a vehicle
        System.out.println("\nDeleting vehicle with ID 3:");
        vCRUD.deleteOperation("Tractor", 3);

        // Display data after updates and deletions
        System.out.println("\nAll vehicles after updates and deletions:");
        vCRUD.selectOperation();
    }
}
