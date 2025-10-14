# Accounting Ledger Appliction

This program is like your personal money tracker.
It helps you keep track of all the money you earn and spend just like a digital checkbook.


-----


## HomeScreen
You can:
- Add a deposit (D) → when you get money (like a paycheck).
- Make a payment (P) → when you spend money (like buying coffee).
- View your ledger (L) → see a list of all your transactions.
- Exit (X) → close the program.

  
-----


### Inside the Ledger
You can:
- See all your transactions (A)
- See only your deposits (D)
- See only your payments (P)
- View reports (R) like how much you earned or spent this month or last year.
- Go back home means it go back to the HomeScreen (H)

  
-------

#### Inside the Reports Section
You can:
- Check transactions for this month, last month, this year, or last year.
- Search by vendor name like seeing everything you bought from “Target”

-------


#### How It Works (Step by Step) ######
# Step 1) Start the program.
- When you run the program, it shows the Home Menu:
<img width="575" height="308" alt="image" src="https://github.com/user-attachments/assets/e0d58405-6231-4b57-ac64-9e4c81ce464d" />

- You enter your comand (like D, P, or L) to choose what you want to do.

---------

# Step 2) Add a Deposit.

If you choose D, the program:

### 1. Asks you for details like:

- Date:

 <img width="561" height="124" alt="image" src="https://github.com/user-attachments/assets/270a0d4c-4ed0-4859-b98c-56f358dbe10b" />

- Time:

 <img width="463" height="43" alt="image" src="https://github.com/user-attachments/assets/835604fb-9b14-4429-85f0-f26b24e8b2e2" />

- Description (what it’s for):

 <img width="307" height="36" alt="image" src="https://github.com/user-attachments/assets/44309641-7097-4a43-8806-0cfebfeb9406" />

- Vendor (who gave or received the money):

 <img width="224" height="43" alt="image" src="https://github.com/user-attachments/assets/f8d66842-9565-45c4-a782-6e1118114620" />

- Amount:

 <img width="373" height="39" alt="image" src="https://github.com/user-attachments/assets/dccb1b1b-925d-41ee-b538-40f8edbc836f" />



### 2.  Then it saves all that info into a CSV file (like transactions.csv).


Example of what’s saved:

<img width="548" height="41" alt="image" src="https://github.com/user-attachments/assets/b3e449dc-ee7b-48d7-8754-e2619432d1de" />

This means on December 4, 2025, at 12:00 AM, ayah received $17.00 from IKEA



### 3. Make a Payment


If you choose P, it’s the same process except the amount is negative (because it’s money going out).

Example:

<img width="559" height="37" alt="image" src="https://github.com/user-attachments/assets/ee70af4a-d229-4a1f-abd0-0cc68adb9927" />

This means On December 12, 2003, at 12:00 AM, Ayah spent $15.00 at IKEA.


### 4. View Your Ledger

When you press L, the program reads all the transactions from the CSV file and shows them on the screen.

You can pick:

<img width="737" height="322" alt="image" src="https://github.com/user-attachments/assets/2d23677c-b17c-44f1-9033-893d28fb3591" />

They’re shown from newest to oldest so you can easily see your latest activity first.


### 5. Run Reports

If you choose R (Reports) inside the ledger, you can filter your data by time or vendor:

<img width="769" height="451" alt="image" src="https://github.com/user-attachments/assets/d1598c04-92a0-423e-90b7-effc54563f75" />

It’s like generating summaries or filtered views of your money history.


### 6. Saving and Reading Data

<img width="2003" height="638" alt="image" src="https://github.com/user-attachments/assets/aa1a3065-00df-42cf-ac40-6b613b66133d" />

What it does:
It takes the details of a financial transaction (like the date, description, and amount) and saves them to a file named transactions.csv.

How it works, step-by-step:

Opens the File: It opens the file transactions.csv in "append" mode. "append" means "add to the end." It ensures the new transaction is written on a new line at the bottom of the file without deleting any of the old ones.

Writes the Data: It takes all the details from a transaction object (t) and writes them into the file in a specific format, with a | (pipe symbol) separating each piece of information. For example:
2024-05-16|14:30|Lunch|Taco Shack|12.50

Handles Errors: If something goes wrong (for example, if the file is missing or can't be opened), instead of the program crashing, it will politely print: "Error saving transaction to file."

### 7. Exit

When you press X, the program stops running, but your data stays saved in the file for the next time you open it.

<img width="711" height="186" alt="image" src="https://github.com/user-attachments/assets/96a08872-653e-46e3-8484-081ccd6e351d" />
