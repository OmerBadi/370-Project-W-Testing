import java.io.*;
import java.util.*;

public abstract class Menu_Abstract {
    Receipt receipt = new Receipt();
    Main main = new Main();

    public void ShowMenu(String FileName) {
        read_Menu_From_File(FileName);
        item_Selector();

    }

    private int [] item_Index = new int[5];
    private String [] item_Name = new String[5];
    private float [] item_Price = new float[5];
    private String [] item_Description = new String[5];
    private String [] item_INFO_Combined = new String[5];

    // Write a method to read menu from a file and store it in an array
    private void read_Menu_From_File(String Filename)
    {
        String SectionReader = "";
        try
        {
            Scanner input = new Scanner(new File(Filename));
            input.useDelimiter(";");
            
            for (int Line = 0; Line < 5 ; Line++)
            {
                for(int Section = 0; Section < 4; Section++)
                {
                    if (Section == 0)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Index : "  + SectionReader);
                        item_Index[Line] = Integer.parseInt(SectionReader);
                    }
                    else if (Section == 1)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Name: "  + SectionReader);
                        item_Name[Line] = SectionReader;
                    }
                    else if (Section == 2)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Price : "  + SectionReader);
                        item_Price[Line] = Float.parseFloat(SectionReader);
                    }
                    else if (Section == 3)
                    {
                        SectionReader = input.next();
                        //System.out.println("This is Description : "  + SectionReader);
                        item_Description[Line] = SectionReader;
                    }
                    item_INFO_Combined[Line] = Items_INFO_Combined_Format(item_Index[Line], item_Name[Line], item_Price[Line], item_Description[Line]);
                }
                input.nextLine();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        PrintMenu();
    }


     private void PrintMenu()
    {
        for (int i = 0; i < item_INFO_Combined.length; i++)
        {
            System.out.println(item_INFO_Combined[i]);
        }
        System.out.println("Press 9 : To Return to Category Order Menu");
        System.out.println("Press 0 : To Exit");
        System.out.println("");
    }


    private String Items_INFO_Combined_Format(int index, String name, float price, String description) 
    {
        String Fromatted_item = index + ". " + name + " - " + price + " - " + description;
        return Fromatted_item;  
    }


//************************************ RECIPE  RELATED - START ************************************
    // These 3 var will increment based on the Users Order, then Send to the Receipt Class 
    float TotalPrice = 0;
    int TotalQuantity = 0;
    String[] Recipt_Items_Formatted = new String[10];


    private String Receipt_Format(String ItemName, float ItemPrice, int ItemQuantity)
    {
        String Receipt_Format_Item = "";
        Receipt_Format_Item += ItemName + " - " + "$"+ ItemPrice + " - "  + "Q. "+ ItemQuantity + "\n";
        return Receipt_Format_Item;
    }

    // For Testing ONLY
    public String Get_Receipt_Format(String Name, float Price , int Quantity)
    {
        String Receipt_Format_Item = "";
        Receipt_Format_Item += Name + " - " + "$"+ Price + " - "  + "Q. "+ Quantity;
        return Receipt_Format_Item;
    }
    private void Send_Items_To_Recipt(String[] FormattedReciptItems)
    {
        // Check How many items in the recipt array
       int Items_in_Recipt_Counter = receipt.ItemsCountInRecipt();
       int initalCounter = 0;

       //If the item is not in the recipt, add it to the recipt start from 0
       if(Items_in_Recipt_Counter == 0)
       {
        initalCounter = 0;
           while(FormattedReciptItems[initalCounter] != null)
           {
               receipt.ReceiptTotalItems[initalCounter] = FormattedReciptItems[initalCounter];
               initalCounter++;
           }
       }
       // if there are items already on the recipt, add the new item to the next empty slot
       else
       {
            initalCounter = 0;
            while(FormattedReciptItems[initalCounter] != null)
            {
                receipt.ReceiptTotalItems[Items_in_Recipt_Counter] = FormattedReciptItems[initalCounter];
                Items_in_Recipt_Counter++;
                initalCounter++;
            }
       }
    }
    
//************************************ RECIPE  RELATED - END ************************************

// Selector
private void item_Selector()
{        
    int ItemsSlected = 0;
    Scanner Reader = new Scanner (System.in);  // Reading from System.in
    System.out.print("Please Enter The Digit that correspondence to what you want: ");
    int b = Reader.nextInt();

    while (b != 0)
    {

        if (b== 9)
        {
            System.out.println("");
            System.out.println("Returning to Category Order Menu");
            receipt.ReceiptTotalPrice += TotalPrice;
            receipt.ReceiptTotalQuantity += TotalQuantity;
            Send_Items_To_Recipt(Recipt_Items_Formatted);
            main.CallCategoryMenu();

        }
        // Printers whatever the User ask and the quantity of it. Then it asks again
        else if (b >= 1 && b <= 5)
        {

            float selectedItem_Price = 0.0f;
            System.out.println("You have selected " + item_Name[b-1]);
    
            // quantity of the item
            System.out.print("Please Enter The Quantity: ");
            int quantity = Reader.nextInt();

            System.out.println("");
            System.out.println("You have selected " + quantity + " " + item_Name[b-1] + "(s)");
            
            // Append Price To Class Total Price
            selectedItem_Price = item_Price[b-1] * quantity;
            TotalPrice += selectedItem_Price;
            TotalQuantity += quantity;
           
           
            // Add Items to Receipt Format
            Recipt_Items_Formatted[ItemsSlected] = Receipt_Format(item_Name[b-1], item_Price[b-1], quantity);
            ItemsSlected++;

            System.out.println("");
        }
        else
        {
            System.out.println("You have selected an invalid option");
            System.out.println("");
        }
        System.out.print("Please Enter The Digit that correspondence to what you want: ");
        b = Reader.nextInt();
    }
    if (ItemsSlected == 0)
    {
        System.out.println("Your Order Has Been Cancelled");
        System.out.println("We Hope You Come Back Soon");
        System.exit(0);
    }
 
}
}