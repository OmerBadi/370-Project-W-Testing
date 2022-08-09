import java.util.*;

public class Menu
{
    // Receipt
    Receipt receipt = new Receipt();


    Breakfast_Menu breakfast_menu = new Breakfast_Menu();
    Lunch_Menu lunch_menu = new Lunch_Menu();
    Dinner_Menu dinner_menu = new Dinner_Menu();
    Dessert_Menu dessert_menu = new Dessert_Menu();
    Drinks_Menu drinks_menu = new Drinks_Menu();

    public void welcomeCustomer()
        {
            print_categoryList();
            show_categorySelection();
        }
    
    private void print_categoryList()
        {
            System.out.println("");
            System.out.println("Welcome to Our Restaurants" );
            System.out.println("");
            System.out.println("1. Breakfast");
            System.out.println("2. Lunch");
            System.out.println("3. Dinner");
            System.out.println("4. Desserts");
            System.out.println("5. Drinks");
            System.out.println("");
            System.out.println("9. To Cancel Order");
            System.out.println("0. To Confirm Your Order");
        }

    private void show_categorySelection()
    {
        Scanner MenuReader = new Scanner(System.in);  // Reading from System.in
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        int n = MenuReader.nextInt();
        //MenuReader.close(); 
        while (n != 9)
        {
            if (n == 1)
            {
                System.out.println("You have selected Breakfast");
                System.out.println("");
                breakfast_menu.Show_Breakfast();
                
            }
            else if (n == 2)
            {
                System.out.println("You have selected Lunch");
                System.out.println("");
                lunch_menu.Show_Lunch();
            }
            else if (n == 3)
            {
                System.out.println("You have selected Dinner");
                System.out.println("");
                dinner_menu.Show_Dinner();
            }
            else if(n == 4)
            {
                System.out.println("You have selected Desserts");
                System.out.println("");
                dessert_menu.Show_Dessert();
            }
            else if (n == 5)
            {
                System.out.println("You have selected Drinks");
                System.out.println("");
                drinks_menu.Show_Drinks();
            }
            else if (n == 0)
            {
                break;
            }
            else
            {
                System.out.println("You have selected an invalid option");
                System.out.println("");
            }
                System.out.println("Please Enter The Digit that correspondence to what you want: ");
                n = MenuReader.nextInt();
        }
        if ( n == 0)
        {
            System.out.println("Your Order Has Been Confirmed");
            receipt.PrintReceipt();
        }
        if (n == 9)
        {
            System.out.println("Your Order Has Been Cancelled");
            System.out.println("We Hope You Come Back Soon");
            System.exit(0);

        }
        
    }
 }

