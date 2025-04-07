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
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack <MazeCell> stack = new Stack<>();
        MazeCell end = maze.getEndCell();
        while(end != null)
        {
            stack.push(end);
            end = end.getParent();
        }
        ArrayList<MazeCell> list = new ArrayList<MazeCell>();
        while(!stack.isEmpty())
        {
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> nextCells = new Stack<MazeCell>();
        MazeCell cellToAdd;
        MazeCell currentCell = maze.getStartCell();
        nextCells.push(currentCell);
        while(!nextCells.isEmpty() && nextCells.peek() != maze.getEndCell())
        {
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if(maze.isValidCell(row - 1,col))
            {
                cellToAdd = maze.getCell(row - 1, col);
                cellToAdd.setParent(currentCell);
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
            currentCell = nextCells.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> nextCells = new LinkedList<>();
        MazeCell cellToAdd;
        MazeCell currentCell = maze.getStartCell();
        nextCells.add(currentCell);
        while(!nextCells.isEmpty())
        {
            currentCell = nextCells.remove();
            if(currentCell == maze.getEndCell())
            {
                return getSolution();
            }
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if(maze.isValidCell(row - 1,col))
            {
                cellToAdd = maze.getCell(row - 1, col);
                cellToAdd.setParent(currentCell);
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
        return null;
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
