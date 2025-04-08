/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Gets the solution to the maze
        Stack <MazeCell> stack = new Stack<>();
        MazeCell end = maze.getEndCell();
        // Backtracks through the parents to get the solution to the maze (in reverse)
        while(end != null)
        {
            stack.push(end);
            end = end.getParent();
        }
        // Utilizes a list to ge the ordered solution
        ArrayList<MazeCell> list = new ArrayList<MazeCell>();
        // Pops the top of the stack to get the cells in proper order until the stack is empty
        while(!stack.isEmpty())
        {
            list.add(stack.pop());
        }
        // Returns the solution in correct order (start to finish)
        return list;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Uses a stack to keep track of the DFS order
        Stack<MazeCell> nextCells = new Stack<MazeCell>();
        MazeCell cellToAdd;
        MazeCell currentCell = maze.getStartCell();
        // Marks the start as the first step to solving the maze with DFS
        nextCells.push(currentCell);
        while(!nextCells.isEmpty())
        {
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            // Checks to see if the neighbors can be explored (in order)
            if(maze.isValidCell(row - 1,col))
            {
                cellToAdd = maze.getCell(row - 1, col);
                cellToAdd.setParent(currentCell);
                // Sets the neighboring cell as explored to make sure we don't go back to it after visiting once
                cellToAdd.setExplored(true);
                nextCells.push(cellToAdd);
            }
            if(maze.isValidCell(row,col + 1))
            {
                cellToAdd = maze.getCell(row , col + 1);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.push(cellToAdd);
            }
            if(maze.isValidCell(row + 1,col))
            {
                cellToAdd = maze.getCell(row + 1, col);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.push(cellToAdd);
            }
            if(maze.isValidCell(row,col - 1))
            {
                cellToAdd = maze.getCell(row , col - 1);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.push(cellToAdd);
            }
            // Uses LIFO to go as far in one path before backtracking
            currentCell = nextCells.pop();
        }
        // Returns the path from start to end
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Uses a queue to keep track of the BFS order
        Queue<MazeCell> nextCells = new LinkedList<>();
        MazeCell cellToAdd;
        MazeCell currentCell = maze.getStartCell();
        nextCells.add(currentCell);
        // Marks the start as the first step to solving the maze with BFS
        while(!nextCells.isEmpty())
        {
            // Removes the first cell in the queue (FIFO)
            currentCell = nextCells.remove();
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if(maze.isValidCell(row - 1,col))
            {
                cellToAdd = maze.getCell(row - 1, col);
                cellToAdd.setParent(currentCell);
                // Sets the neighboring cell as explored to make sure we don't go back to it after visiting once
                cellToAdd.setExplored(true);
                nextCells.add(cellToAdd);
            }
            if(maze.isValidCell(row,col + 1))
            {
                cellToAdd = maze.getCell(row , col + 1);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.add(cellToAdd);
            }
            if(maze.isValidCell(row + 1,col))
            {
                cellToAdd = maze.getCell(row + 1, col);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.add(cellToAdd);
            }
            if(maze.isValidCell(row,col - 1))
            {
                cellToAdd = maze.getCell(row , col - 1);
                cellToAdd.setParent(currentCell);
                cellToAdd.setExplored(true);
                nextCells.add(cellToAdd);
            }
        }
        // Returns the path from start to end
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
