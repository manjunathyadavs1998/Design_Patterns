package src.com.dassault.systemes.MultiThreading;

/**
 * Problem Statement: Bank Account Example
 * Imagine a bank account that starts with a balance of 1000. Two threads are simultaneously trying to withdraw money from the account. Each thread will deduct a certain amount from the account balance. The problem arises when both threads access the account balance at the same time, leading to incorrect results due to a race condition.
 *
 * For example, let's assume that both threads attempt to withdraw 600 from the account. Here's the sequence of operations:
 *
 * Thread 1 reads the balance (1000).
 * Thread 2 reads the balance (1000) before thread 1 updates it.
 * Both threads deduct 600, resulting in a balance of 400 instead of 0.
 * In this case, even though both threads tried to withdraw 600, the final balance should have been 400. However, due to the race condition, the final balance might not be correct.
 *
 * Solution: Synchronization
 * To fix the race condition, we use synchronization. Synchronizing a method or a block of code ensures that only one thread can execute that part of the code at any given time.
 *
 * Java provides the synchronized keyword, which can be used to control access to critical sections of code that deal with shared resources.
 */
class BankAccount {
    private int balance = 1000;

    // Method to withdraw money
    public void withdraw(int amount) throws Exception {

        if(getBalance()<amount){
            throw new Exception("Insufficient Balance");
        }

        // Read the balance (race condition)
        int currentBalance = balance;

        // Simulate some delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update the balance (this is where the race condition happens)
        balance = currentBalance - amount;
    }

    // Getter for balance
    public int getBalance() {
        return balance;
    }
}

public class RaceConditionExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Thread 1 - Withdraw 600
        Thread t1 = new Thread(() -> {
            try {
                account.withdraw(600);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1: Balance after withdrawal: " + account.getBalance());
        });

        // Thread 2 - Withdraw 600
        Thread t2 = new Thread(() -> {
            try {
                account.withdraw(600);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2: Balance after withdrawal: " + account.getBalance());
        });

        // Start both threads
        t1.start();
        t2.start();
    }
}

