// ecListFuncs.h
// CSCI 455 Spring 2021, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef EC_LIST_FUNCS_H
#define EC_LIST_FUNCS_H

#include <string>



//*************************************************************************
// Node type used for lists
struct Node {
   int data;
   Node *next;

   Node(int item);
   Node(int item, Node *n);

};


typedef Node * ListType;



//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in ecListFuncs.cpp)


/*
 * Note: examples of linked lists here are shown as a space separated list of numbers surrounded by
 * parentheses.  This is also the format of the string produced by listToString, below.
 *
 */

/*
 * listToString
 *
 * PRE: list is a well-formed list.
 *
 * converts the list to a string form that has the following format shown by example.
 * the list is unchanged by the function.
 *
 *   string format:
 *
 *   "()"        an empty list
 *   "(3)        a list with one element, 3
 *   "(3 4 5)"   a list with multiple elements: 3 followed by 4 followed by 5
 *
 * Note: to get credit your function must use the exact format shown in the string, e.g. no extra
 * spaces.
 */
std::string listToString(ListType list);


/*
 * buildList
 *
 * PRE: listString only contains numbers (valid integer format) and spaces
 *
 * creates and returns a linked list from a string of space separated numbers
 *
 *
 * Examples:
 *  listString         return value of buildList(listString)
 *
 *    ""               ()
 *    "-32"            (-32)
 *    "     -32   "    (-32)
 *    "1 3 2"          (1 3 2)
 *    "  1 3 2"        (1 3 2)
 *
 * Hint: see C++ library hints on assignment description.
 */
ListType buildList(const std::string & listString);

// reverse a linkedlist
ListType reverseList(ListType list);

/*
 * removeLastInstance
 *
 * PRE: list is a well-formed list
 *
 * removes the last instance of target in list.  If target is not in list list is unchanged.
 */
void removeLastInstance(ListType & list, int target);


/*
 * splitAtIndex
 *
 * PRE: list is a well-formed list and index >= 0
 *
 * Assuming nodes are numbered starting from 0, splits list into two sub-lists as follows:
 * "a" will contain all the elements up to, but not including, the node at the given index
 * from the original list.  And "b" will contain all the elements after
 * the node at the given index in the original list.  Otherwise the values in the new
 * lists will be in the same order as they were in the original list.
 * If index >= the length of the list, all the elements will be in "a",
 * and "b" will be NULL.
 * After the operation, list will have the value NULL (the function destroys the list, because
 * it reuses nodes form the original list).
 *
 * NOTE: this function does not create any nodes, but reuses most or all of the nodes from
 * the original list.
 *
 * Examples (list' indicates the value of list after the call):
 *
 *  list        splitOnIndex   a        b           list'
 *  (7 4 4 3 9)  2            (7 4)     (3 9)       ()
 *  (7 4 2 3 9)  0            ()        (4 2 3 9)   ()
 *  (1 2 3 3 2)  4            (1 2 3 3) ()          ()
 *  ()           3            ()        ()          ()
 *  (7 2 3)      2            (7 2)     ()          ()
 *  (7 2 3)      3            (7 2 3)   ()          ()
 *  (3)          0            ()        ()          ()
 *  (3 5)        0            ()        (5)         ()
 *  (3 5)        1            (3)       ()          ()
 */
void splitAtIndex(ListType &list, int index, ListType &a, ListType &b);

//*************************************************************************

#endif
