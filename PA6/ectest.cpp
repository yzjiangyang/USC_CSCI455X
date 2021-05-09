/*  Name: Yang Jiang
 *  USC NetID: yjiang24
 *  CS 455 Sping 2021
 *  Extra credit assignment
 *
 *  ectest.cpp
 *
 *  a non-interactive test program to test the functions described in ecListFuncs.h
 *
 *    to run it use the command:   ectest
 *
 *  Note: this uses separate compilation.  You put your list code ecListFuncs.cpp
 *  Code in this file should call those functions.
 */


#include <iostream>
#include <string>
#include "ecListFuncs.h"

using namespace std;

int main () {
    // test listToString by creating some examples
    Node *node1 = new Node(1);
    Node *node2 = new Node(2, node1);
    Node *node3 = new Node(3, node2);
    string actualNode3 = listToString(node3);
    string expctedNode3 = "(3 2 1)";
    string Node3Matched = actualNode3 == expctedNode3 ? "YES" : "FAILED";
    cout << "Test if 'listToString' can convert a list to a string successfully. Input list 3->2->1, listToString result should be '(3 2 1)'" << endl;
    cout << "Expected result: " + expctedNode3 << endl;
    cout << "Actual result: " + actualNode3 << endl;
    cout << "Does the actual result match the expected result? " + Node3Matched << endl;
    cout << "-----------------------------------------------" << endl;

    // test empty list
    Node *emptyNode = NULL;
    string actualEmptyNode = listToString(emptyNode);
    string expctedEmptyNode = "()";
    string emptyNodeMatched = actualEmptyNode == expctedEmptyNode ? "YES" : "FAILED";
    cout << "Test if 'listToString' can convert an empty list to a string successfully. Input list NULL, listToString result should be '()'" << endl;
    cout << "Expected result: " + expctedEmptyNode << endl;
    cout << "Actual result: " + actualEmptyNode << endl;
    cout << "Does the actual result match the expected result? " + emptyNodeMatched << endl;
    cout << "-----------------------------------------------" << endl;

    // test buildList
    string listString = "7 4 3 4 9";
    Node *list = buildList(listString);
    string buildListActual = listToString(list);
    string buildListExpected = "(7 4 3 4 9)";
    string buildListmatched = buildListActual == buildListExpected ? "YES" : "FAILED";
    cout << "Test if 'buildList' can build a list successfully. Input '7 4 3 4 9', listToString result should be '(7 4 3 4 9)'" << endl;
    cout << "Expected result: " + buildListExpected << endl;
    cout << "Actual result: " + buildListActual << endl;
    cout << "Does the actual result match the expected result? " + buildListmatched << endl;
    cout << "-----------------------------------------------" << endl;

    // test removeLastInstance with list 7->4->3->4->9 (it has been build)
    removeLastInstance(list, 4);
    string removeLastActual = listToString(list);
    string removeLastExpected = "(7 4 3 9)";
    string removeLastmatched = removeLastActual == removeLastExpected ? "YES" : "FAILED";
    cout << "Test if 'removeLastInstance' can remove the last instance 4 successfully. Input list 7->4->3->4->9 and target 4, listToString result should be '(7 4 3 9)'" << endl;
    cout << "Expected result: " + removeLastExpected << endl;
    cout << "Actual result: " + removeLastActual << endl;
    cout << "Does the actual result match the expected result? " + removeLastmatched << endl;
    cout << "-----------------------------------------------" << endl;

    // another test removeLastInstance with list 3->2->1
    string str = "3 2 1";
    Node* nodeStr = buildList(str);
    removeLastInstance(nodeStr, 4);
    string remove4Actual = listToString(nodeStr);
    string remove4Expected = "(3 2 1)";
    string remove4matched = remove4Actual == remove4Expected ? "YES" : "FAILED";
    cout << "Test if 'removeLastInstance' can remove the last instance 4 successfully. Input list 3->2->1 and target 4, listToString result should be '(3 2 1)'" << endl;
    cout << "Expected result: " + remove4Expected << endl;
    cout << "Actual result: " + remove4Actual << endl;
    cout << "Does the actual result match the expected result? " + remove4matched << endl;
    cout << "-----------------------------------------------" << endl;

    // test splitAtIndex with list 7->4->3->9 (it has been built)
    ListType list1 = NULL;
    ListType list2 = NULL;
    splitAtIndex(list, 2, list1, list2);
    string actualA = listToString(list1);
    string expectedA = "(7 4)";
    string matchedA = actualA == expectedA ? "YES" : "FAILED";
    string actualB = listToString(list2);
    string expectedB = "(9)";
    string matchedB = actualB == expectedB ? "YES" : "FAILED";
    string actualList = listToString(list);
    string expectedList = "()";
    string matchedList = actualList == expectedList ? "YES" : "FAILED";
    cout << "Test if 'splitAtIndex' can split the list at index 2 successfully. Input list 7->4->3->9 and index 2, a should be '(7 4)', b should be '(9)', and list should be '()'" << endl;
    cout << "Expected result of a: " + expectedA << endl;
    cout << "Actual result of a: " + actualA << endl;
    cout << "Does the actual result of a match the expected result of a? " + matchedA << endl;

    cout << "Expected result of b: " + expectedB << endl;
    cout << "Actual result of b: " + actualB << endl;
    cout << "Does the actual result of b match the expected result of b? " + matchedB << endl;

    cout << "Expected result of list: " + expectedList << endl;
    cout << "Actual result of list: " + actualList << endl;
    cout << "Does the actual result of list match the expected result of list? " + matchedList << endl;
    cout << "-----------------------------------------------" << endl;

    // another test splitAtIndex with list 3->2->1
    string s = "3 2 1";
    Node *node = buildList(s);
    ListType listA = NULL;
    ListType listB = NULL;
    splitAtIndex(node, 0, listA, listB);
    string actualANode3 = listToString(listA);
    string expectedANode3 = "()";
    string matchedANode3 = actualANode3 == expectedANode3? "YES" : "FAILED";
    string actualBNode3 = listToString(listB);
    string expectedBNode3 = "(2 1)";
    string matchedBNode3 = actualBNode3 == expectedBNode3 ? "YES" : "FAILED";
    string actualListNode3 = listToString(node);
    string expectedListNode3 = "()";
    string matchedListNode3 = actualListNode3 == expectedListNode3 ? "YES" : "FAILED";
    cout << "Test if 'splitAtIndex' can split the list at index 0 successfully. Input list 3->2->1 and index 0, a should be '()', b should be '(2 1)', and list should be '()'" << endl;
    cout << "Expected result of a: " + expectedANode3 << endl;
    cout << "Actual result of a: " + actualANode3 << endl;
    cout << "Does the actual result of a match the expected result of a? " + matchedANode3 << endl;

    cout << "Expected result of b: " + expectedBNode3 << endl;
    cout << "Actual result of b: " + actualBNode3 << endl;
    cout << "Does the actual result of b match the expected result of b? " + matchedBNode3 << endl;

    cout << "Expected result of list: " + expectedListNode3 << endl;
    cout << "Actual result of list: " + actualListNode3 << endl;
    cout << "Does the actual result of list match the expected result of list? " + matchedListNode3 << endl;
    cout << "-----------------------------------------------" << endl;

    // another test splitAtIndex with empty list
    string emptyS = "";
    Node *nodeEmpty = buildList(emptyS);
    ListType listAA = NULL;
    ListType listBB = NULL;
    splitAtIndex(nodeEmpty, 3, listAA, listBB);
    string actualAEmptyNode = listToString(listAA);
    string expectedAEmptyNode = "()";
    string matchedAEmptyNode = actualAEmptyNode == expectedAEmptyNode? "YES" : "FAILED";
    string actualBEmptyNode = listToString(listBB);
    string expectedBEmptyNode = "()";
    string matchedBEmptyNode = actualBEmptyNode == expectedBEmptyNode ? "YES" : "FAILED";
    string actualListEmptyNode = listToString(nodeEmpty);
    string expectedListEmptyNode = "()";
    string matchedListEmptyNode = actualListEmptyNode == expectedListEmptyNode ? "YES" : "FAILED";
    cout << "Test if 'splitAtIndex' can split the empty list at index 3 successfully. Input list NULL and index 3, a should be '()', b should be '()', and list should be '()'" << endl;
    cout << "Expected result of a: " + expectedAEmptyNode << endl;
    cout << "Actual result of a: " + actualAEmptyNode << endl;
    cout << "Does the actual result of a match the expected result of a? " + matchedAEmptyNode << endl;

    cout << "Expected result of b: " + expectedBEmptyNode << endl;
    cout << "Actual result of b: " + actualBEmptyNode << endl;
    cout << "Does the actual result of b match the expected result of b? " + matchedBEmptyNode << endl;

    cout << "Expected result of list: " + expectedListEmptyNode << endl;
    cout << "Actual result of list: " + actualListEmptyNode << endl;
    cout << "Does the actual result of list match the expected result of list? " + matchedListEmptyNode << endl;
    cout << "-----------------------------------------------" << endl;

    return 0;
}
