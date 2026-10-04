# ATM Interface

## 📌 Project Overview

This project is a console-based ATM Interface developed using Java and Object-Oriented Programming (OOP) concepts.

The application simulates basic ATM operations such as user authentication, deposit, withdrawal, money transfer, and transaction history.

This project was developed as part of the Oasis Infobyte Java Development Internship.

---

## 🎯 Objective

The main objective of this project is to create a simple ATM simulation that allows users to:

- Login using User ID and PIN
- Perform deposits
- Withdraw money
- Transfer money to another account
- View transaction history
- Handle insufficient balance
- Limit incorrect PIN attempts

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Maven
- Lombok
- ArrayList
- Scanner
- LocalDateTime

---

## ✨ Features

### 1. User Authentication

The user logs in using:

- User ID
- PIN

The application allows a maximum of 3 incorrect PIN attempts.

After 3 incorrect attempts, access is denied.

### 2. Main Menu

After successful login, the following options are available:

1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit

### 3. Transaction History

All transactions performed during the current session are stored using an ArrayList.

The transaction history displays:

- Transaction type
- Amount
- Description
- Date and time

### 4. Withdraw

The user can enter the amount to withdraw.

The application:

- Validates the amount
- Checks available balance
- Deducts the amount from the account
- Records the transaction
- Displays the remaining balance

If the balance is insufficient, the application displays an appropriate message.

### 5. Deposit

The user can enter an amount to deposit.

The application:

- Validates the amount
- Adds the amount to the account balance
- Records the transaction
- Displays the updated balance

### 6. Transfer

The user can transfer money to another account using the recipient's Account ID.

The application:

- Finds the recipient account
- Validates the amount
- Checks available balance
- Deducts money from the sender
- Adds money to the recipient
- Records the transaction

### 7. Quit

The user can select the Quit option to safely exit the ATM application.

---

## 🏗️ Project Structure

    atmInterface
    │
    ├── src
    │   └── main
    │       └── java
    │           ├── oasis.atmInterface
    │           │   └── Main.java
    │           │
    │           └── oasis.atmInterface.model
    │               ├── Account.java
    │               ├── Atm.java
    │               ├── Bank.java
    │               └── Transaction.java
    │
    ├── pom.xml
    ├── README.md
    └── screenshots

---

## 🧩 Java Classes

### Account.java

The `Account` class represents a bank account.

It stores:

- Account ID
- User ID
- PIN
- Balance

It also provides methods for:

- Deposit
- Withdrawal

### Transaction.java

The `Transaction` class represents an individual transaction.

It stores:

- Transaction type
- Amount
- Description
- Date and time

### Bank.java

The `Bank` class manages multiple accounts.

It provides functionality to:

- Add accounts
- Find an account using User ID
- Find an account using Account ID

### Atm.java

The `Atm` class handles the main ATM operations.

It is responsible for:

- User authentication
- PIN validation
- Main menu
- Deposit
- Withdrawal
- Transfer
- Transaction history
- Exit operation

### Main.java

The `Main` class is the entry point of the application.

It:

1. Creates the bank
2. Creates sample accounts
3. Adds accounts to the bank
4. Creates the ATM
5. Starts the ATM application

---

## 🧠 OOP Concepts Used

### Encapsulation

The account fields are private and accessed through appropriate methods.

The account balance is modified through methods such as deposit and withdraw instead of allowing direct modification.

### Classes and Objects

The project uses multiple classes:

- Account
- Transaction
- Bank
- Atm
- Main

Objects are created from these classes to represent accounts, transactions, the bank, and the ATM.

### Abstraction

Operations such as deposit, withdrawal, and transfer hide the internal implementation details from the user.

### Object Interaction

Different objects work together to perform ATM operations.

Main creates and starts the ATM, the ATM interacts with the Bank, and the Bank manages Account objects.

---

## 🔐 Authentication Flow

    Start
      ↓
    Enter User ID
      ↓
    Check Account
      ↓
    Enter PIN
      ↓
    Correct PIN?
      ↓
    ┌───────────────┐
    │               │
    Yes             No
    │               │
    ↓               ↓
    Login       Attempts < 3?
    │               │
    ↓               ├── Yes → Try Again
    Main Menu       │
                    ↓
                3 Attempts
                    ↓
                Access Denied

---

## 💰 Transaction Flow

### Deposit

    Enter Amount
         ↓
    Validate Amount
         ↓
    Update Balance
         ↓
    Create Transaction
         ↓
    Display New Balance

### Withdraw

    Enter Amount
         ↓
    Validate Amount
         ↓
    Check Balance
         ↓
    Sufficient Balance?
      ┌───────┴───────┐
      │               │
     Yes              No
      │               │
      ↓               ↓
    Withdraw     Insufficient Funds
      │
      ↓
    Create Transaction
      │
      ↓
    Display Balance

### Transfer

    Enter Recipient Account ID
              ↓
    Find Recipient Account
              ↓
    Enter Amount
              ↓
    Check Balance
              ↓
    Deduct From Sender
              ↓
    Add To Recipient
              ↓
    Create Transaction

---

## 📋 Sample Accounts

The application starts with sample accounts for testing.

### Account 1

- Account ID: `ACC001`
- User ID: `chirag`
- PIN: `1234`
- Balance: ₹10000

### Account 2

- Account ID: `ACC002`
- User ID: `rahul`
- PIN: `5678`
- Balance: ₹15000

---

## ▶️ How to Run

### Prerequisites

Make sure the following are installed:

- Java JDK
- IntelliJ IDEA / Eclipse / STS
- Maven

### Run Using IDE

1. Open the project in your IDE.
2. Navigate to:

   `src/main/java/oasis.atmInterface/Main.java`

3. Run `Main.java`.
4. The ATM application will start in the console.

---

## 🖥️ Sample Output

### Login

    =================================
            WELCOME TO ATM
    =================================

    Enter User ID: chirag
    Enter PIN: 1234

    Login successful!
    Welcome, chirag

### Main Menu

    ========== MAIN MENU ==========
    1. Transaction History
    2. Withdraw
    3. Deposit
    4. Transfer
    5. Quit

    Enter choice:

### Deposit

    Enter choice: 3

    Enter amount: 5000

    Deposit successful.
    New Balance: ₹15000

### Withdraw

    Enter choice: 2

    Enter amount: 2000

    Withdrawal successful.
    Remaining Balance: ₹13000

### Transfer

    Enter choice: 4

    Enter recipient Account ID: ACC002

    Enter amount: 1000

    Transfer successful.
    Remaining Balance: ₹12000

### Transaction History

    Enter choice: 1

    ====== TRANSACTION HISTORY ======

    Type: DEPOSIT | Amount: ₹5000.0 | Cash deposited | Time: ...
    Type: WITHDRAW | Amount: ₹2000.0 | Cash withdrawn | Time: ...
    Type: TRANSFER | Amount: ₹1000.0 | Transfer to ACC002 | Time: ...

### Insufficient Funds

    Enter choice: 2

    Enter amount: 50000

    Insufficient Funds.

### Incorrect PIN

    Enter User ID: chirag

    Enter PIN: 1111
    Incorrect PIN.
    Attempts remaining: 2

    Enter PIN: 2222
    Incorrect PIN.
    Attempts remaining: 1

    Enter PIN: 3333
    Incorrect PIN.

    Access Denied.

### Quit

    Enter choice: 5

    Thank you for using ATM.

---

## 📸 Screenshots

Screenshots of the working application are included in the `screenshots` folder.

The screenshots demonstrate:

- Successful login
- Main menu
- Deposit operation
- Withdrawal operation
- Transfer operation
- Transaction history
- Insufficient funds handling
- Incorrect PIN handling

---

## 📦 Project Requirements Checklist

- [x] Console-based ATM application
- [x] User ID and PIN authentication
- [x] Maximum 3 incorrect PIN attempts
- [x] Transaction History
- [x] Withdraw
- [x] Deposit
- [x] Transfer
- [x] Balance validation
- [x] Insufficient Funds handling
- [x] Transaction storage using ArrayList
- [x] Multiple Java classes
- [x] Object-Oriented Programming
- [x] Console-based interaction

---

## 🚀 Future Improvements

The project can be extended in the future with:

- Database integration
- User registration
- PIN encryption
- Persistent transaction history
- GUI interface
- REST APIs
- Spring Boot backend
- MySQL/PostgreSQL database
- Persistent account balance

---

## 👨‍💻 Author

**Chirag Goel**

Java Development Intern

### Internship

This project was developed as part of the **Oasis Infobyte Java Development Internship**.

**Track:** Java Development

**Project:** ATM Interface