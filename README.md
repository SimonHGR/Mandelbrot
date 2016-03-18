Mandelbrot pattern
------------------

Simple Mandelbrot viewer, intended for use as basis of concurrency
exercises. Built with single threaded calculator that runs in the
event thread.

Options for improvement:

1) Run the calculation in a background thread, allowing normal
event processing to continue.

2) Run the calculation across multiple threads in an Executor
allowing concurrent computaion on hardware threaded machines

3) Run the calculation across a parallel, unordered, stream.

