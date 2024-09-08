package database;

public class Test {

    public static void main(String[] args) {
        DBOperations dbOps = new DBOperations();
        
        //INSERTING OF DETAILS
        
        //dbOps.insertOperation(1, "Boeing", "747", 4);
        //dbOps.insertOperation(2, "Airbus", "A320", 2);
        //dbOps.insertOperation(3, "Embraer", "Phenom 100EX", 2);
        
        //SELECTING AND DISPLAYING ALL DETAILS FROM DATABASE    
        
        System.out.println("=======Welcome, view all the aircrafts below==================");
        
        dbOps.selectOperation();
        
        //DELETING ROWS BASED ON ID NUMBER
        
        //dbOps.deleteOperation(3);
        
        
        //UPDATING DATA IN ROWS BASED ON ID NUMBER
        
        //dbOps.updateOperation(1, "Boeing", "747", 4);
        
        
    }

    public void selectOperations(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
