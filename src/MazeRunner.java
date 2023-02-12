import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This is the main class.
 * 
 * @author genesis guerra@merrimack.edu
 * CSC 6002 - Foundations of Programming 2
 * Project 6 - Maze Runner
 */
public class MazeRunner {
    
    public static void main(String args[]) {       
        Scanner scanner = new Scanner(System.in);
        Maze maze = new Maze(); //creates a new maze
        int choice = 0;
        
        do {
            System.out.println("What do you want to do?");
            System.out.println("1. Load a new maze");
            System.out.println("2. Solve the maze");
            System.out.println("3. End Program");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    String filename;
                    ListInterface<String> line = null;
                    do{
                        try{
                            System.out.println("Enter maze file name:");
                            filename = scanner.next();//update the name of the file to be the input
                            line = readLinesFromFile(filename);//pulls up the file and sets each line into a linked list
                        }catch(FileNotFoundException ex){
                            System.out.println(ex.getMessage());
                            System.out.println("Enter maze file name:");
                            filename = scanner.next();
                        }
                    }while(line == null);
                    for(int i = 0; i < line.getLength(); i++){//loops through the linked list
                        String lines = line.getEntry(i);//gets eahc line 
                        for(int j = 0; j < lines.length(); j++){//take the length of the line to split into chars
                            maze.setMazeRoom(j, i, lines.charAt(j));//set the maze room according to the char found
                            if(lines.charAt(j) == 'S'){//record the start
                                String colRow = j + "" + i;
                                maze.setStartLabel(colRow);
                            }
                            else if(lines.charAt(j) == 'G'){//record the end
                                String colRow = j + "" + i;
                                maze.setEndLabel(colRow);
                            }
                        }
                    }
                    System.out.print(maze.toString());//prints constructed maze
                    maze.createGraph();//creates a directed graph to be able to traverse from start to end, if any
                    break;
                case 2:
                    if(maze.isEmpty()){
                        System.out.println("Maze is empty");
                        break;
                    }
                    else{
                        maze.findPath();//finds a path and prints a linked list representation of the path
                        System.out.println(maze.toString());//updates the maze to have the path indicated by 'O'
                    }
                    break;
                case 3:
                    break;             
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }    
    
    public static ListInterface<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        ListInterface<String> lines = new LinkedList<>();
        // create a File object based on the fileName provided
        File file = new File(fileName);
        Scanner scanner = new Scanner(file); // use the file instead of the keyboard for input
        // go through each line of the file
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.insert(i++, line);
        }
        return lines;
    }

}
    