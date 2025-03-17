// BankAccount class
class BankAccount {
    // Attributes
    private String accountNumber;
    private String ownerName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // Method to display account information
    public void displayInfo() {
        System.out.println("Nomor Rekening: " + accountNumber);
        System.out.println("Name Pemilik: " + ownerName);
        System.out.println("Saldo: Rp" + balance);
        System.out.println();
    }

    // Method to deposit money
    public void depositMoney(double amount) {
        balance += amount;
        System.out.println(ownerName + " menyetor Rp" + amount + ". Saldo sekarang: Rp" + balance);
    }

    // Method to withdraw money
    public void withdrawMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(ownerName + " menarik Rp" + amount + ". (Berhasil) Saldo sekarang: Rp" + balance);
        } else {
            System.out.println(ownerName + " menarik Rp" + amount + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini: Rp" + balance);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create two BankAccount objects
        BankAccount account1 = new BankAccount("202410370110039", "Firdaus", 508080.0);
        BankAccount account2 = new BankAccount("202410370110040", "Firmansyah", 1080009.0);

        // Display initial account information
        account1.displayInfo();
        account2.displayInfo();

        // Perform deposit transactions
        account1.depositMoney(208000.0);
        account2.depositMoney(508000.0);

        // Perform withdrawal transactions
        account1.withdrawMoney(508000.0);
        account2.withdrawMoney(508000.0);

        // Display final account information
        account1.displayInfo();
        account2.displayInfo();
    }
}