// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI 455 PA5
// Spring 2021

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

// default hash table constructor
Table::Table() { 
	hashSize = HASH_SIZE; // default hash size
	hashTable = new ListType[hashSize]; // create a hash table with default hash size
	totalSize = 0; // empty
}

// hash table constructor with given hSize
Table::Table(unsigned int hSize) { 
	hashSize = hSize;
	hashTable = new ListType[hashSize]; // create a hash table with with size of hashSize
	totalSize = 0; // empty
}

// return the pointer that point to the value of the given key
int * Table::lookup(const string &key) {
   ListType &node = hashTable[hashCode(key)]; // get the linkedlist
   return listLookup(node, key); // return the pointer
}

// Remove a pair, return true if it is removed successfully, return false if it does not exist
bool Table::remove(const string &key) {
   ListType &node = hashTable[hashCode(key)]; // get the linkedlist
   bool isRemoved =  listRemove(node, key);  // get the result about if the pair is removed or not.
   if (isRemoved) {
   	totalSize -= 1; // update the total size if removing successfully
   }

   return isRemoved; // return true if removing successfully, otherwise, false.
}

// Insert a new pair, return true if it is inserted successfully, return false if it does not exist
bool Table::insert(const string &key, int value) {
   ListType &node = hashTable[hashCode(key)]; // get the linkedlist
   bool isInserted =  listInsert(node, key, value); // get the result about if the pair is inserted successfully or not
   if (isInserted) {
   	totalSize += 1; // update the total size if inserting successfully
   }

   return isInserted; // return true if inserting successfully, otherwise, false.
}

// get the number of entries
int Table::numEntries() const {
   return totalSize;
}

// Prints out the number of entries in the table
void Table::printAll() const {
	for (int i = 0; i < hashSize; i++) {
		ListType list = hashTable[i]; // get each linkedlist
		listPrint(list); // print out
	}
}

// Prints out statistics about the hash table at this point. (Calls hashStats() method)
void Table::hashStats(ostream &out) const {
	cout << "number of buckets: " << hashSize << endl; // print the number of buckets
	cout << "number of entries: " << numEntries() << endl; // print the number of entries
	cout << "number of non-empty buckets: " << getNumNonEmptyBucket() << endl; // print the number of non-empty buckets
	cout << "longest chain: " << getLongestChainSize() << endl; // print the longest chain
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here

// calculate the number of non-empty buckets from the hash table
int Table::getNumNonEmptyBucket() const {
	unsigned int nonEmptySize = 0; // initialize nonEmptySize = 0
	for (int i = 0; i < hashSize; i++) {
		if (hashTable[i] != NULL) { // if the bucket is not empty
			nonEmptySize += 1; // update nonEmptySize
		}
	}

	return nonEmptySize; // return the number of non-empty buckets
}

// calculate the longest chain size
int Table::getLongestChainSize() const {
	unsigned int longest = 0; // initialize longest = 0
	for (int i = 0; i < hashSize; i++) {
		if (listSize(hashTable[i]) > longest) { // if current linkedlist size > longest
			longest = listSize(hashTable[i]); // update longest 
		}
	}

	return longest; // return the size of longest chain.
}


