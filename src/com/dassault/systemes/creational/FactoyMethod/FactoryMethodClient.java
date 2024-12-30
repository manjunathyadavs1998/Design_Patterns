package src.com.dassault.systemes.creational.FactoyMethod;

// Step 1: Product - PaymentMethod (abstract class or interface)
interface PaymentMethod {
    void processPayment(double amount);
}

// Step 2: Concrete Products - Different payment methods
class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing bank transfer payment of $" + amount);
    }
}

// Step 3: Creator - PaymentProcessor (abstract class with a factory method)
abstract class PaymentProcessor {
    // Factory method - to be implemented by subclasses
    public abstract PaymentMethod createPaymentMethod();

    // Client code uses this method to process a payment
    public void process(double amount) {
        PaymentMethod paymentMethod = createPaymentMethod();
        paymentMethod.processPayment(amount);
    }
}

// Step 4: Concrete Creators - Implement the factory method in subclasses
class CreditCardPaymentProcessor extends PaymentProcessor {
    @Override
    public PaymentMethod createPaymentMethod() {
        return new CreditCardPayment();
    }
}

class PayPalPaymentProcessor extends PaymentProcessor {
    @Override
    public PaymentMethod createPaymentMethod() {
        return new PayPalPayment();
    }
}

class BankTransferPaymentProcessor extends PaymentProcessor {
    @Override
    public PaymentMethod createPaymentMethod() {
        return new BankTransferPayment();
    }
}

public class FactoryMethodClient {
    public static void main(String[] args) {
        // Simulate client code

        // Client chooses the payment processor (based on user input, etc.)
        PaymentProcessor paymentProcessor = new CreditCardPaymentProcessor();

        // Client calls process() to initiate the payment
        paymentProcessor.process(100.0);  // Output: Processing credit card payment of $100.0

        // Client chooses a different payment processor
        paymentProcessor = new PayPalPaymentProcessor();
        paymentProcessor.process(200.0);  // Output: Processing PayPal payment of $200.0

        // Another payment method
        paymentProcessor = new BankTransferPaymentProcessor();
        paymentProcessor.process(300.0);  // Output: Processing bank transfer payment of $300.0
    }
}

