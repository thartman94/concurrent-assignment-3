## Concurrent Programing - Assignment 3

Rowan University

Professor: Patrick McKee

Semester: Spring 2022

## Instructions:

This assignment will serve as an alteration to your Assignment 2.

In Assignment 2 you had a menu that allowed you to perform your prime
calculations in a single threaded way or an unbounded way. In this assignment we
will attempt to perform the task in a bounded way using the Executor framework.

Add a menu item to your submission from Assignment 2 (if your assignment 2 does
not have a finalized grade by 4/9 please contact me). This menu item will say
"Bounded Threadpool".

You will then implement a solution to the same problem we have had before
(primes between 2-n with factorization for non primes) but you will do it using
a bounded thread pool. This bounded thread pool should use either Callables or
Futures to relay information. Do not use Runnables for this implementation.

There should be a sub menu that allows the user to submit the size of the
bounded thread pool with an option for 0 being a default implementation that
uses the number of cores on the clients machine + 1.

When finished, do a small write up that you will publish in your submission
folder that details the runtime differences between the 3, and what the thread
pool capacity was that gave you the best performance.

Submit your src folder as an archive.
