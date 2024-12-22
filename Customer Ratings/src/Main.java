import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static int cn=0;
    private static int productCount,turkishCount=0,doctorCount=0;

    //two dimensional list and linked list
    private static int[][] ratingsArray;
    private static int[] ratingsTotal;
    private static int[] turkishTotalRatings,doctorTotalRatings;//turkish customers ratings total
    private static String[] productNames;
    private static LinkedList customerList = new LinkedList();

    //method Reading the data from file
    private static void readDataFromFile(String path) {
        try {
             File file = new File(path);
             Scanner scanner = new Scanner(file);

            //getting product count from first integer
            String productLine = scanner.nextLine();

            //making a list from product names
            productNames = productLine.split(",");
            productCount = Integer.parseInt(productNames[0]);
            ratingsArray = new int[200][productCount+1];
            ratingsTotal = new int[productCount];
            turkishTotalRatings = new int[productCount];
            doctorTotalRatings = new int[productCount];

            for(int a=0;a<productCount;a++){
                ratingsTotal[a] = 0;
                turkishTotalRatings[a] = 0;
                doctorTotalRatings[a] = 0;
            }
            while (scanner.hasNextLine()) {
                //getting customer info with splitting the line
                String customerInfo = scanner.nextLine();
                String[] cSplitInfo = customerInfo.split(",");
                int customerNo = Integer.parseInt(cSplitInfo[0]);
                String firstName = cSplitInfo[1];
                String lastName = cSplitInfo[2];
                String country = cSplitInfo[3];
                if(country.equalsIgnoreCase("Turkey")){
                    turkishCount++;
                }
                String city = cSplitInfo[4];
                String occupation = cSplitInfo[5];
                CustomerData customer = new CustomerData(firstName, lastName, country, city, occupation);
                customerList.addWithOrder(customerNo,new CustomerData(customer)); // Add a copy to the list

                //ratings of the customer
                String rtngs = scanner.nextLine();
                String[] rtngsSplit = rtngs.split(",");

                ratingsArray[cn][0] = customerNo;
                for (int i = 1; i <= productCount; i++) {
                    ratingsArray[cn][i] = Integer.parseInt(rtngsSplit[i-1]);
                    ratingsTotal[i-1] += ratingsArray[cn][i]; //adding to total ratings arrray to calculate avg rating
                    if(country.equalsIgnoreCase("Turkey")){//if turkish adding it to total turkish ratings array
                        turkishTotalRatings[i-1] += ratingsArray[cn][i];
                    }
                    if(occupation.equalsIgnoreCase("Doctor")){//if doctor adding it to total doctor ratings array
                        doctorTotalRatings[i-1] += ratingsArray[cn][i];
                    }
                }
                cn++;
            }
            scanner.close();
            System.out.println("Data read successfully from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        //variables
        int similarity=0,smallest=9999999,lastRate=0,sameCount=1;//variables to calculate last rating

        Scanner scan = new Scanner(System.in);
        while (true){
            //Menu
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1 - Read a file that you enter");
            System.out.println("2 - Add a customer");
            System.out.println("3 - Print average ratings of all products ");
            System.out.println("4 - Print ratings but only turkish customers considered");
            System.out.println("5 - Print ratings but turkish customers not considered");
            System.out.println("6 - Print ratings but only doctor customers considered");
            System.out.println("7 - Print all customers infos (linked list)");
            System.out.println("8 - Print all ratings given by customers (2 dimensional array)");
            System.out.println("0 - Exit");
            System.out.print("Enter your selection (0-7): ");

            int choice = scan.nextInt();
            // invalid input check
            while (choice<0 || choice>8){
                System.out.print("Invalid input try again (0-8): ");
                choice = scan.nextInt();
            }

            switch (choice){
                case 1:
                    System.out.print("Enter your file path: ");
                    scan.nextLine();
                    String path = scan.nextLine();
                    readDataFromFile(path);
                    break;
                case 2:
                    // Adding customer
                    System.out.print("Customer No: ");
                    int customerNo = scan.nextInt();
                    ratingsArray[cn][0] = customerNo;//1st column is customer no

                    //getting info from keyboard
                    scan.nextLine();
                    System.out.print("Name: ");
                    String name = scan.nextLine();
                    System.out.print("Surname: ");
                    String surname = scan.nextLine();
                    System.out.print("Country: ");
                    String country = scan.nextLine();
                    System.out.print("City: ");
                    String city = scan.nextLine();
                    System.out.print("Occupation: ");
                    String occupation = scan.nextLine();

                    if(country.equalsIgnoreCase("Turkey")){
                        turkishCount++;
                    }
                    if(occupation.equalsIgnoreCase("Doctor")){
                        doctorCount++;
                    }
                    //Adding customer to the list
                    CustomerData customerData = new CustomerData(name, surname, country, city, occupation);
                    customerList.addWithOrder(customerNo,customerData);

                    //Getting ratings
                    for (int i=1; i<productCount; i++){
                        System.out.print("Enter rating for product "+productNames[i]+" (1-5) : ");
                        ratingsArray[cn][i] = scan.nextInt();
                        while(ratingsArray[cn][i]<1 || ratingsArray[cn][i]>5){
                            System.out.print("Invalid input try again (1-5): ");
                            ratingsArray[cn][i] = scan.nextInt();
                        }
                        ratingsTotal[i-1] += ratingsArray[cn][i];
                        if(country.equalsIgnoreCase("Turkey")){
                            turkishTotalRatings[i-1] += ratingsArray[cn][i];
                        }
                        if(occupation.equalsIgnoreCase("Doctor")){
                            doctorTotalRatings[i-1] += ratingsArray[cn][i];
                        }
                    }

                    // created a similarity and smallest variable to check smallest similarity
                    for(int i=0; i<cn; i++){
                        for(int j=1;j<productCount;j++){ //getting the similarity value for each customer
                            similarity += Math.abs(ratingsArray[i][j]-ratingsArray[cn][j]);
                        }
                        System.out.println("similarity between "+i+" "+cn+": "+similarity);
                        if (similarity == smallest){ // if similarity equals increasing the same count to calculate avg
                            lastRate += ratingsArray[i][productCount];
                            sameCount++;
                        }
                        if (similarity<smallest){
                            smallest = similarity;
                            lastRate = ratingsArray[i][productCount];
                            sameCount = 1;
                        }
                        similarity = 0;
                    }
                    ratingsArray[cn][productCount] = Math.floorDiv(lastRate,sameCount);//finding last products rating by similarity
                    ratingsTotal[productCount-1] += ratingsArray[cn][productCount];//adding it to total ratings array
                    System.out.println("Your rating for the final product from similarity: "+ratingsArray[cn][productCount]);
                    cn++; // adding 1 to customer number to count customers
                    scan.nextLine();
                    break;
                case 3:
                    // Printing avg ratings
                    System.out.println("Printing average ratings");
                    for(int k=0; k<productCount; k++){
                        System.out.println("Average rating for product "+productNames[k+1]+" is: "+Math.floorDiv(ratingsTotal[k],cn));
                    }
                    break;
                case 4:
                    // Printing turkish customers ratings
                    System.out.println("Average ratings of products, but only Turkish customers are considered: ");
                    for (int k=0; k<productCount; k++){
                        System.out.println(productNames[k+1]+" : "+Math.floorDiv(turkishTotalRatings[k],turkishCount));
                    }
                    break;
                case 5:
                    // Printing non turkish customers ratings
                    System.out.println("Average ratings of products, but Turkish customers are not considered: ");
                    for (int k=0; k<productCount; k++){
                        int nonTurkeyCount = cn - turkishCount;
                        System.out.println(productNames[k+1]+" : "+Math.floorDiv(ratingsTotal[k]-turkishTotalRatings[k],nonTurkeyCount));
                    }
                    break;
                case 6:
                    // Printing doctor customers ratings
                    System.out.println("Average ratings of products, but only Doctor customers are considered: ");
                    for (int k=0; k<productCount; k++){
                        System.out.println(productNames[k+1]+" : "+Math.floorDiv(doctorTotalRatings[k],doctorCount));
                    }
                    break;
                case 7:
                    // Printing customer list
                    System.out.println();
                    System.out.println("Printing all Customer data: ");
                    customerList.printList();
                    break;
                case 8:
                    // Printing 2 dimensional list
                    System.out.println("Customer numbers and their ratings for products : ");
                    System.out.print("      C.No  ");
                    for(int n=1; n<=productCount; n++){
                        System.out.print(productNames[n]+"  ");
                    }
                    System.out.println();
                    for(int l=0; l<cn; l++){
                        System.out.print("       ");
                        for(int m=0; m<=productCount; m++){
                            System.out.print(ratingsArray[l][m]+"  ");
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}