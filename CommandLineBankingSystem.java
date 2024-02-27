import java.util.Scanner;

public class CommandLineBankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two sample accounts
        Account account1 = new Account("123456", "Savings", 1000.0);
        Account account2 = new Account("789012", "Checking", 500.0);

        while (true) {
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBalance(account1);
                    break;
                case 2:
                    deposit(account1);
                    break;
                case 3:
                    withdraw(account1);
                    break;
                case 4:
                    transfer(account1, account2);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void viewBalance(Account account) {
        System.out.println("Account Balance: $" + account.getBalance());
    }

    private static void deposit(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private static void transfer(Account sourceAccount, Account destinationAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter transfer amount: $");
        double amount = scanner.nextDouble();
        sourceAccount.transferTo(destinationAccount, amount);
        System.out.println("Transfer successful.");
    }
}