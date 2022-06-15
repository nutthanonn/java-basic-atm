import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Menu {
    Scanner sc = new Scanner(System.in);
    HashMap<String, Account> data = new HashMap<String, Account>();
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public void checkLogin() throws IOException {
        try {
            System.out.print("\n---> Login <---\n");
            System.out.print("\nusername: ");
            String usernameLogin = sc.nextLine();
            System.out.print("\npassword: ");
            String passwordLogin = sc.nextLine();
            if (data.get(usernameLogin) != null) {
                Account acc = (Account) data.get(usernameLogin);
                if(acc.getPassword().equals(passwordLogin)){
                    moneyMenu(acc);
                }else{
                    System.out.println("\n--> Wrong password!");
                }
            }else{
                System.out.println("\n --> Username not found");
            }

        } catch (InputMismatchException e) {
           System.out.println("\n--> Invalid Input");
           sc.next();
        }
    }


    public void createAccount() throws IOException {
        try {
            System.out.print("\n---> Register <---\n");
            System.out.print("\nCreate username: ");
            String username = sc.nextLine();
            System.out.print("\nCreate password: ");
            String password = sc.nextLine();
            if (data.get(username) == null){
                data.put(username, new Account(username, 10000, password));
                System.out.println("\n --> Create Success");
            }else{
                System.out.println("\n --> !!! Username already use !!!");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("\nError to create");
            sc.next();
        }
    }

    public void moneyMenu(Account acc) {
        boolean end = true;
        while (end) {
            try {
                System.out.println("\n---> Select your choice <---\n");
                System.out.println("Checkings Account Balance: " + moneyFormat.format(acc.getBalance()));
                System.out.println("\n1. Deposit");
                System.out.println("2. withdraw");
                System.out.println("3. statement");
                System.out.println("0. exit");
                System.out.print("\nSelect menu options: ");
                String userChoice = sc.nextLine();
                switch (userChoice) {
                    case "1":
                        System.out.print("\nEnter your money: ");
                        String deposit = sc.nextLine();
                        acc.setDeposit(Double.parseDouble(deposit));
                        System.out.println("Success to deposit");
                        break;
                    case "2":
                        System.out.print("\nEnter your money to withdraw: ");
                        String withdraw = sc.nextLine();
                        if(acc.getBalance()-Double.parseDouble(withdraw) >= 0) {
                            acc.setWithdraw(Double.parseDouble(withdraw));
                            System.out.println("Success to withdraw");
                        }else{
                            System.out.println("You don't have enough money");
                        }
                        break;
                    case "3":
                        System.out.println("This feature is comming soon...");
                        break;
                    case "0":
                        end = false;
                        break;
                    default:
					    System.out.println("\n ---> Invalid Choice.");
                }
            }catch(InputMismatchException e){
                System.out.println("Menu error plese try again");
                sc.next();
            }
        }
    }    

    public void mainMenu() throws IOException{
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n1. Login");
                System.out.println("2. Register");
                System.out.println("0. Leave");
                System.out.print("\nChoose menu: ");
                String userChoice = sc.nextLine();

                switch (userChoice) {
                    case "1":
                        checkLogin();
                        break;
                    case "2":
                        createAccount();
                        break;
                    case "0":
                        end = true;
                        break;
                    default:
                        System.out.println("\n---> Invalid Choice.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n---> Invalid Choice.");
                sc.next();
            }
        } 
		System.out.println("\n ---> Thank You.\n");
        sc.close();
        System.exit(0);
    }

}