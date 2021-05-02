// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI 455 PA5
// Spring 2021

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>
void handleInsert(std::string &name, int score, Table *grades);
void handleChange(std::string &name, int score, Table *grades);
void handleLookup(std::string &name, Table *grades);
void handleRemove(std::string &name, Table *grades);
void printHelp();
int getInputScore(std::string cmd, std::string &name);
void cmdExucute(std::string cmd, std::string &name, int score, Table *grades);

using namespace std;

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   string cmd = ""; // initilize input command
   string name = ""; // initilize input name
   bool exit = false; // exit = false means the program is not quit

   while (!exit) { // while not quit
      cout << "cmd> ";
      cin >> cmd;
      if (cmd == "quit") { // if input is "quit"
         exit = true; // update exist to be true, need to quit the program
      }
      else {
         int score = getInputScore(cmd, name); // else, get the input score
         cmdExucute(cmd, name, score, grades); // excute the command
      }
   }
   return 0;
}


/*
   getInputScore gets the input score from the command
   @param cmd the input command
   @param name the inpue name
   @return the value of the input score
*/
int getInputScore(string cmd, string &name) {
   int score = -1;
   if (cmd == "lookup" || cmd == "remove") {
      cin >> name;
   }
   if (cmd == "insert" || cmd == "change") {
      cin >> name;
      cin >> score;
   }

   return score;
}

/*
   @param grades the hash table
*/
void cmdExucute(string cmd, string &name, int score, Table *grades) {
   if (cmd == "insert") {
      handleInsert(name, score, grades);
   }

   else if (cmd == "change") {
      handleChange(name, score, grades);
   }

   else if (cmd == "lookup") {
      handleLookup(name, grades);
   }

   else if (cmd == "remove") {
      handleRemove(name, grades);
   }

   else if (cmd == "print") {
      grades->printAll();
   }

   else if (cmd == "size") {
      cout << grades->numEntries() <<endl;
   }

   else if (cmd == "stats") {
      grades->hashStats(cout);
   }

   else if (cmd == "help") {
      printHelp();
   }

   else {
      cout << "ERROR: invalid command. Please input \"help\" to get the command summary."<< endl;
   }
}

void handleInsert(string &name, int score, Table *grades) {
   bool isInserted = grades->insert(name, score);
   if (!isInserted) {
      cout << "Error: the name already exists." << endl;
   }
}

void handleChange(string &name, int score, Table *grades) {
   int *p = grades->lookup(name);
   if (p == NULL) {
      cout << "Error: the name does not exist." << endl;
   }
   else {
      *p = score;
   }
}

void handleLookup(string &name, Table *grades) {
   int *p = grades->lookup(name);
   if (p == NULL) {
      cout << "Error: the name does not exist." << endl;
   }
   else {
      cout << *p << endl;
   }
}

void handleRemove(string &name, Table *grades) {
   bool isRemoved = grades->remove(name);
   if (!isRemoved) {
      cout << "Error: the name does not exist." << endl;
   }
}

void printHelp() {
   cout << "insert name score: Insert the name and score to the grade table." << endl;
   cout << "change name newscore: Change the score for the name." << endl;
   cout << "lookup name: Lookup the name, and then print out the score, or a message indicating that student is not in the table." << endl;
   cout << "remove name: Remove the name." << endl;
   cout << "print: Print out all names and scores in the table." << endl;
   cout << "size: Print out the number of entries in the table." << endl;
   cout << "stats: Print out statistics about the hash table" << endl;
   cout << "help: Print out a command summary." << endl;
   cout << "quit: Exit the program." << endl;
}