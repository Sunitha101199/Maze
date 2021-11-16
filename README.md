
### Maze

>You are given an n*n matrix, You will start from a source cell in the matrix and need to reach the destination cell avoiding the obstacles along the way. Your task is to find the lowest number of cells (or lowest distance or the path with lowest distance) you have to pass through in order to reach the destination.

### Model 

>We model the above system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the person reached the end from the start if there is a full site in the bottom row. In other words, a person reaches the end if we fill all open sites connected to the top row and that process fills some open sites on the bottom row. 

### Problem

>In this problem, researchers are more interested in the following question: We have different implementations of solving the problem with the case study of Dynamic connectivity which uses Union Find. Try experimenting with 4 different implementations of Dynamic connectivity. 

* Quick-find
* Quick-union
* Weighted quick-union
* Weighted quick-union with path compression

>Find out which implementation of the Dynamic Connectivity is the effective implementation and why?

### Report

>The report should have the input size vs time for every implementation. Using the visualization, draw the conclusions for every implementation related to the input size. Prove that which is the effective implementation to solve the problem. 
