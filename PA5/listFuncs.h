// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI 455 PA5
// Spring 2021


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// get the result about if the list contains the target or not.
bool isContain(ListType &list, const std::string &target);
// remove the target if the list contains the target and return true, otherwise, return false
bool listRemove(ListType &list, const std::string &target);
// get the pointer that points to the value of the target 
int *listLookup(ListType &list, const std::string &target);
// print all entries of the list
void listPrint(ListType &list);
// insert new entries, and return true. return false if the list does not contain the entry
bool listInsert(ListType &list, const std::string &target, int value);
// get the size of the list
int listSize(ListType &list);












// keep the following line at the end of the file
#endif
