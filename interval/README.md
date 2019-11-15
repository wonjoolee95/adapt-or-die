Intervals are not some kind of data structures or algorithms, but I think they deserve their own section since they're so common in
interview questions.

The key point in intervals is -- how to define (and find) an intersection between the intervals. The simplest and most widely used way 
is to use a min heap (or sort) by the start time. Then as we poll from the heap, we can see if there is an intersection or not. 
Whenever we see interval-related question, think about how to define an intersection!
