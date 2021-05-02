// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI 455 PA5
// Spring 2021


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

// get the result about if the list contains the target or not.
bool isContain(ListType &list, const string &target) {
	Node *node = list;
	while (node != NULL) {
		if (node->key == target) { // if the key equals target
			return true; // the list has target, return true
		}
		node = node->next; // update the node
	}
	return false; // return false if target does not exist in list
}

// remove the target if the list contains the target and return true, otherwise, return false
bool listRemove(ListType &list, const string &target) {
	if (list == NULL) { // if list is empty
		return false; // return false
	}

	Node *node = list;
	if (node->key == target) { // if the first entry key equals to the traget
		list = list->next; // update the list
		delete node; // reclaim the memory
		return true; // return true
	}
	while (node->next != NULL) {
		if (node->next->key == target) { // if the next entry key equals to the target
			Node *deadGuy = node->next;
			delete deadGuy; // reclaim the memory
			node->next = node->next->next; // update next node
			return true; // return true
		}
		node = node->next; // update node if the next entry key does not equal to target
	}

	return false; // return false if target does not exist in list
}

// get the pointer that points to the value of the target 
int *listLookup(ListType &list, const string &target) {
	Node *node = list;
	while (node != NULL) {
		if (node->key == target) { // if node key equals to the target
			return &(node->value); // return the address of the value
		}
		node = node->next; // update node if the key does not equal to target
	}

	return NULL; // return NULL if does not find target
}

// print all entries of the list
void listPrint(ListType &list) {
	Node *node = list;
	while (node != NULL) {
		cout << node->key << " " << node->value << endl; // print key and value
		node = node->next; // update node
	}
}

// insert new entries, and return true. return false if the list does not contain the entry
bool listInsert(ListType &list, const string &target, int value) {
	if (isContain(list, target)) { // if the list does not contain the entry,
		return false; // retrun false
	}

	Node *newNode = new Node(target, value); // create new linkedlist for the new entry
	Node *node = list;
	if (node == NULL) { // if the list is empty
		list = newNode; // update list
		return true; // return true after adding
	}

	newNode->next = list; // append list to newNode
	list = newNode; // update list
	return true; // return true after inserting
}

// get the size of the list
int listSize(ListType &list) {
	Node *node = list;
	int size = 0; // intialize size = 0
	while (node != NULL) { // while not empty
		size += 1; // update size by adding 1
		node = node->next; // update node
	}

	return size; // return the size of the list
}