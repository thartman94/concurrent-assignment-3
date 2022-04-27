## Concurrent Programing - Assignment 3

Rowan University 
Professor: Patrick McKee 
Semester: Spring 2022

## Writeup:

Machine specs: cpu Intel i5-4690K (overclocked via ASUS automated overclocking)
cores 4 ram 16.0 GB

The following times are the result of running the calculation on a value of
10,000

    Single Threaded  	20.0ms
    Unbounded 			5128.0ms
    Bounded (1)			63.0ms
    Bounded (2)			46.0ms
    Bounded (3)			17.0ms
    Bounded (4)   		12.0ms
    Bounded (5)			15.0ms *** Default
    Bounded (6) 		19.0ms
    Bounded (10)		19.0ms
    Bounded (20) 		15.0ms
    Bounded (30) 		11.0ms
    Bounded (40) 		13.0ms
    Bounded (50) 		17.0ms
    Bounded (100)		11.0ms
    Bounded (500)		14.0ms
    Bounded (10,000)	15.0ms

Single threaded opperation was fairly quick, while unbounded operation was
remarkably bad. This is due to the overhead of creating so many threads and
switching between them.

What was interesting is the results of the bounded opperation. Using only 1
thread returned the worst time, which was improved moderately by increasing to 2
threads. Once three threads were used, the times were consistently faster than
the sequential method. What was interesting was the fact that the bounded
threadpool method remianed consistent (between 11ms and 19ms) all the way out to
10,000. I'm curious if there was something about the implementation of the
threadpool method that is causing it to prevent unnessisary work and overhead,
even when a rediculous number of threads is selected by the user.

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
