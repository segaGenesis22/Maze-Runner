/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for Maze class
 * 
 * @author Ed Grzyb
 */
public class MazeTest {
    
    public MazeTest() {
    }
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Maze maze = new Maze();        
        boolean result = maze.isEmpty();
        assertTrue(result);
    }
    @Test
    public void testSetGetMazeRoom() {
        System.out.println("setMazeRoom");
        int column = 0;
        int row = 0;
        char c = 'X';
        Maze maze = new Maze();
        maze.setMazeRoom(column, row, c);        
        assertEquals('X', maze.getMazeRoom(column, row));
    }
    /**
     * Creates a 10 x 10 room with one horizontal path only
     * to do some basic tests on a graph
     */
    @Test
    public void testCreateGraphHorizontalPath() {
        System.out.println("testCreateGraphHorizontalPath");
        Maze maze = new Maze();
        char c = 'X';
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (y == 3) {
                    c = '-';
                    if (x == 0) {
                        c = 'S';
                    } else if (x == 9) {
                        c = 'G';
                    }
                    maze.setMazeRoom(x, y, c);                    
                } else {
                    c = 'X';//added this otherwise the graph had many Gs on the maze
                    maze.setMazeRoom(x, y, c);
                }
            }
        }
        System.out.println(maze.toString());
        maze.createGraph();
        DirectedGraph<String> mazeGraph = maze.getMazeGraph();
        Vertex<String> start = mazeGraph.getVertex("03");
        assertEquals("03", maze.getStartLabel());
        assertTrue(start.hasEdge("13"));
        assertFalse(start.hasEdge("04"));
        Vertex<String> pathVertex = mazeGraph.getVertex("53");
        assertTrue(pathVertex.hasEdge("43"));
        assertTrue(pathVertex.hasEdge("63"));
        assertFalse(pathVertex.hasEdge("54"));
        Vertex<String> end = mazeGraph.getVertex("93");
        assertEquals("93", maze.getEndLabel());
        assertTrue(end.hasEdge("83"));
        assertFalse(end.hasEdge("92"));
        
    }    
        
}
