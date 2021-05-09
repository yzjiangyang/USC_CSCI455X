/*  Name: Yang Jiang
 *  USC NetID: yjiang24
 *  CS 455 Sping 2021
 *
 *  See ecListFuncs.h for specification of each function.
 *
 *  NOTE: remove the print statements below as you implement each function
 *  or you will receive no credit for that function
 *
 */
#include <string>
#include <cassert>
// for istringstream
#include <sstream>

// iostream only needed for the "not implemented yet" messages in starter code
#include <iostream>

#include "ecListFuncs.h"

using namespace std;

// *********************************************************
// Node constructors: do not change
Node::Node(int item) {
    data = item;
    next = NULL;
}

Node::Node(int item, Node *n) {
    data = item;
    next = n;
}
// *********************************************************
// reverse a linkedlist
ListType reverseList(ListType list) {
    Node* newList = NULL;
    while (list != NULL) {
        Node* nodeNext = list->next;
        list->next = newList;
        newList = list;
        list = nodeNext;
    }

    return newList;
}

ListType buildList(const string & listString) {
    istringstream iss(listString);
    string num;
    Node *node = NULL;
    while (iss >> num) {
        node = new Node(stoi(num), node);
    }
    return reverseList(node); // reverse the linkedlist
}


string listToString(ListType list) {
    if (list == NULL) {
        return "()";
    }

    Node *p = list;
    string str = "(";
    while (p->next != NULL) {
        str = str + to_string(p->data) + " ";
        p = p->next;
    }
    str = str + to_string(p->data) + ")";

    return str;
}


void removeLastInstance(ListType & list, int target) {
    if (list == NULL) {
        return;
    }
    Node *reversedList = reverseList(list);// reverse the linkedlist, then remove the 1st instance
    Node *p = reversedList;
    if (p->data == target) {
        reversedList = reversedList->next;
        delete p;
    } else {
        while (p->next != NULL && p->next->data != target) {
            p = p->next;
        }
        if (p->next != NULL) { // if target found
            Node *deadGuy = p->next;
            p->next = p->next->next;
            delete deadGuy;
        }
    }
    list = reverseList(reversedList);
}


void splitAtIndex(ListType &list, int index, ListType &a, ListType &b) {
    assert(index >= 0);
    a = NULL;
    b = NULL;
    if (list == NULL) {
        return;
    }
    Node *p = list;
    if (index == 0) {
        list = list->next;
        b = list;
        delete p;
    } else {
        for (int i = 0; i < index - 1; i++) {
            p = p->next;
        }
        b = p->next->next;
        Node *deadGuy = p->next;
        p->next = NULL;
        delete deadGuy;
        a = list;
    }
    list = NULL;
}
