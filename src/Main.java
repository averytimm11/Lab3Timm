public class Main {
    public static void main(String[] args) {
            MySqlCrud mysql = new MySqlCrud();
            MongoCrud mongo = new MongoCrud();

            System.out.println("--- CREATE ---");
            Customer c1 = new Customer("Alice Smith", "alice@test.com", 150.00);
            Customer c2 = new Customer("Bob Jones", "bob@test.com", 50.00);
            Customer c3 = new Customer("Charlie Brown", "charlie@test.com", 10.00);

            mysql.create(c1);
            mysql.create(c2);
            mysql.create(c3);

            mongo.create(c1);
            mongo.create(c2);
            mongo.create(c3);

            System.out.println("--- READ ALL INITIAL RECORDS ---");
            System.out.println("MySQL Data:");
            mysql.readAll().forEach(System.out::println);
            System.out.println("MongoDB Data:");
            mongo.readAll().forEach(System.out::println);

            System.out.println("--- UPDATE ALICE ---");
            c1.setName("Alice M. Smith");
            c1.setBalance(300.00);
            mysql.update(c1);
            mongo.update(c1);

            System.out.println("--- DELETE CHARLIE ---");
            mysql.delete(c3.getCustomerId());
            mongo.delete(c3.getCustomerId());

            System.out.println("--- FINAL DATABASE STATE ---");
            System.out.println("MySQL Total Remaining: " + mysql.readAll().size());
            System.out.println("MongoDB Total Remaining: " + mongo.readAll().size());
        }
}
