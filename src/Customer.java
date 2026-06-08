/**
 * Project: Lab 3 Java MySQL MongoDB CRUD
 * Purpose Details: Demonstrate CRUD operations in MySQL and MongoDB
 * Course: IST242
 * Author: Avery Timm
 * Date Developed: 06/07/2026
 * Last Date Changed: 06/07/2026
 * Rev: 1.0
 */
public class Customer {
        private int customerId;
        private String name;
        private String email;
        private double balance;

        public Customer() {}

        public Customer(String name, String email, double balance) {
            this.name = name;
            this.email = email;
            this.balance = balance;
        }

        public Customer(int customerId, String name, String email, double balance) {
            this.customerId = customerId;
            this.name = name;
            this.email = email;
            this.balance = balance;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "ID: " + customerId + " | Name: " + name + " | Email: " + email + " | Balance: $" + balance;
        }
}
