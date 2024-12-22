# Customer Rating System

## Overview
This project is a **Customer Rating System** that allows customers to rate products, and provides various functionalities such as:
- Adding new customers.
- Calculating and displaying average ratings for products.
- Filtering ratings based on specific criteria such as Turkish customers or doctors.
- Displaying customer data stored in a linked list.

The program supports data reading from a CSV file that contains customer information and their ratings for a set of products.

## Features
- **Read Data from File**: The program reads customer data from a specified CSV file.
- **Add Customers**: Users can manually add customer data and ratings.
- **Average Ratings**: Displays average ratings for products, with options to filter by:
  - Turkish customers.
  - Customers excluding Turkish ones.
  - Doctor customers.
- **Linked List Implementation**: A custom linked list is used to store and display customer data in an ordered manner (by customer number).
- **Ratings Calculation**: The program allows customers to rate products on a scale of 1 to 5, with the option to calculate ratings based on similarity to previous customers.

## File Format

### Customer Information:
The file contains the following data format for each customer:
```
Customer Number, First Name, Last Name, Country, City, Occupation
```

### Product Ratings:
Ratings for each product follow the customer information. The format for ratings is as follows:
```
Rating for Product A, Rating for Product B, Rating for Product C, Rating for Product D, Rating for Product E
```

### Example:
```
5,A,B,C,D,E
121,Ali,Ceviz,Turkey,Isparta,Computer Engineer
3,4,3,5,1
201,John,Smith,USA,New Jersey,Doctor
5,2,1,4,3
162,Veli,Tuz,Turkey,Ankara,Doctor
4,3,4,4,2
203,Zeynep,Demir,Turkey,Antalya,Lawyer
3,2,4,3,2
105,Mario,Gomez,Germany,Munich,Football player
4,1,3,2,1
```

## How to Use

### 1. **Run the Program**
   - Compile and run the `Main.java` file.
   - Follow the menu options in the console to interact with the system.

### 2. **Menu Options**
The program provides the following options:
- **1**: Read customer data from a CSV file.
- **2**: Add a new customer and their ratings.
- **3**: Print average ratings for all products.
- **4**: Print ratings but only Turkish customers are considered.
- **5**: Print ratings excluding Turkish customers.
- **6**: Print ratings but only doctor customers are considered.
- **7**: Print all customer information (linked list).
- **8**: Print all ratings given by customers (2D array).
- **0**: Exit the program.

### 3. **Adding a New Customer**
   - Input customer details including their name, country, city, occupation, and product ratings (1 to 5).

### 4. **Calculating Average Ratings**
   - The program calculates and displays average ratings for products based on different filters (e.g., only Turkish customers or doctors).

## Class Structure

- **Main**: The main entry point of the program that handles user input, reads from a file, and displays results.
- **CustomerData**: A class representing customer information (name, surname, country, etc.).
- **LinkedList**: A custom linked list implementation that stores customer data and allows for adding customers in an ordered manner.
- **Node**: A private class used to create nodes for the linked list.

## Example Output
```
Menu:
1 - Read a file that you enter
2 - Add a customer
3 - Print average ratings of all products
4 - Print ratings but only turkish customers considered
5 - Print ratings but turkish customers not considered
6 - Print ratings but only doctor customers considered
7 - Print all customers infos (linked list)
8 - Print all ratings given by customers (2 dimensional array)
0 - Exit
Enter your selection (0-7): 1
Enter your file path: /path/to/data.csv
Data read successfully from file.

Enter your selection (0-7): 3
Printing average ratings:
Average rating for product A is: 3
Average rating for product B is: 2
...
```

## Technologies Used
- Java (JDK 8 or later)
- File I/O (Reading from CSV file)
- Linked List Data Structure

## Requirements
- Java 8 or later
- A CSV file containing customer data and ratings.

