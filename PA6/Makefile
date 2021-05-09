# Makefile for CS 455 extra credit assgt Spring 2021
#
#
#     make ectest
#        Makes ectest executable
#


CXXFLAGS = -ggdb -Wall -std=c++11
OBJS = ecListFuncs.o ectest.o


ectest: $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o ectest

$(OBJS): ecListFuncs.h
