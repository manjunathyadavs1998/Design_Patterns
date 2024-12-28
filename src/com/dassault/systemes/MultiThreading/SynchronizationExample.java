package src.com.dassault.systemes.MultiThreading;


class BankAccount2 {
    private int balance = 1000;

    public void withdraw(int amount) {

        synchronized(this) {

            if(getBalance()<amount){
                System.out.print("Insufficient Balance");
                return;
            }// Synchronize only the critical section
            int currentBalance = balance;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = currentBalance - amount;
        }
    }

    public int getBalance() {
        return balance;
    }
}
class BankAccount1 {
    private int balance = 1000;

    // Synchronized method to ensure only one thread can access it at a time
    public synchronized void withdraw(int amount) {

        if(getBalance()<amount){
            System.out.print("Insufficient Balance");
            return;
        }
        // Read the balance
        int currentBalance = balance;

        // Simulate some delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update the balance
        balance = currentBalance - amount;
    }

    // Getter for balance
    public int getBalance() {
        return balance;
    }
}

public class SynchronizationExample {
    public static void main(String[] args) {
        BankAccount1 account = new BankAccount1();

        // Thread 1 - Withdraw 600
        Thread t1 = new Thread(() -> {
            account.withdraw(600);
            System.out.println("Thread 1: Balance after withdrawal: " + account.getBalance());
        });

        // Thread 2 - Withdraw 600
        Thread t2 = new Thread(() -> {
            account.withdraw(600);
            System.out.println("Thread 2: Balance after withdrawal: " + account.getBalance());
        });

        // Start both threads
        t1.start();
        t2.start();


        System.out.println("Using sync method");
        BankAccount2 account1 = new BankAccount2();

        // Thread 1 - Withdraw 600
        Thread t3 = new Thread(() -> {
            account1.withdraw(600);
            System.out.println("Thread 1: Balance after withdrawal: " + account1.getBalance());
        });

        // Thread 2 - Withdraw 600
        Thread t4 = new Thread(() -> {
            account1.withdraw(600);
            System.out.println("Thread 2: Balance after withdrawal: " + account1.getBalance());
        });

        // Start both threads
        t3.start();
        t4.start();
    }
}

