/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class creates a maze and a directed graph based on the maze as well as 
 * finding a path in the maze from start to end by utilizing the graph to traverse the maze.
 * 
 * @author genesis guerra@merrimack.edu
 * CSC 6002 - Foundations of Programming 2
 * Project 6 - Maze Runner 
 */
public class Maze {
    private char mazeRooms[][] = new char[10][10];
    private DirectedGraph<String> mazeGraph = new DirectedGraph<>(); ;
    private int MAZE_SIZE = 0;
    private String startLabel = "";
    private String endLabel = "";
    
    public Maze(){
    }
    /**
     * This method...
     * 
     * @param column
     * @param row
     * @param c 
     */
    public void setMazeRoom(int column, int row, char c){
        this.mazeRooms[column][row] = c;
        MAZE_SIZE = MAZE_SIZE + 1;
    }
    /**
     * This method...
     * 
     * @param column
     * @param row
     * @return 
     */
    public char getMazeRoom(int column, int row){
        return mazeRooms[column][row];
    }
    /**
     * 
     */
    public void createGraph(){
        //makes horizontal edges 
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(mazeRooms[j][i] == '-' && mazeRooms[j + 1][i] == '-'){
                    String begin = j + "" + i;
                    String end = (j + 1) + "" + i;
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }
                if((mazeRooms[j][i] == '-' && mazeRooms[j + 1][i] == 'S') || (mazeRooms[j][i] == 'S' && mazeRooms[j + 1][i] == '-')){
                    String begin = j + "" + i;
                    String end =  (j + 1) + "" + i;
                    //record start labels
                    if(mazeRooms[j][i] == '-' && mazeRooms[j + 1][i] == 'S'){
                        this.startLabel = end;
                    }
                    else if(mazeRooms[j][i] == 'S' && mazeRooms[j + 1][i] == '-'){
                        this.startLabel = begin;
                    }
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }
                if((mazeRooms[j][i] == '-' && mazeRooms[j + 1][i] == 'G') || (mazeRooms[j][i] == 'G' && mazeRooms[j + 1][i] == '-')){
                    String begin = j + "" + i;
                    String end = (j + 1) + "" + i;
                    //record end labels
                    if(mazeRooms[j][i] == '-' && mazeRooms[j + 1][i] == 'G'){
                        this.endLabel = end;
                    }
                    else if(mazeRooms[j][i] == 'G' && mazeRooms[j + 1][i] == '-'){
                        this.endLabel = begin;
                    }
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }  
            }
        }
        
        //makes vertical edges
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(mazeRooms[i][j] == '-' && mazeRooms[i][j + 1] == '-'){
                    String begin = i + "" + j;
                    String end = i + "" + (j + 1);
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }
                if(mazeRooms[i][j] == '-' && mazeRooms[i][j + 1] == 'S' || mazeRooms[i][j] == 'S' && mazeRooms[i][j + 1] == '-'){
                    String begin = i + "" + j;
                    String end = i + "" + (j + 1);
                    //record start labels
                    if(mazeRooms[i][j] == '-' && mazeRooms[i][j + 1] == 'S'){
                        this.startLabel = end;
                    }
                    else if(mazeRooms[i][j] == 'S' && mazeRooms[i][j + 1] == '-'){
                        this.startLabel = begin;
                    }
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }
                if(mazeRooms[i][j] == '-' && mazeRooms[i][j + 1] == 'G' || mazeRooms[i][j] == 'G' && mazeRooms[i][j + 1] == '-'){
                    String begin = i + "" + j;
                    String end = i + "" + (j + 1);
                    //record the end labels
                    if(mazeRooms[i][j] == '-' && mazeRooms[i][j + 1] == 'G'){
                        this.endLabel = end;
                    }
                    else if(mazeRooms[i][j] == 'G' && mazeRooms[i][j + 1] == '-'){
                        this.endLabel = begin;
                    }
                    //add vertices
                    mazeGraph.addVertex(begin);
                    mazeGraph.addVertex(end);
                    //add doubles edges because its a directed graph
                    mazeGraph.addEdge(begin, end);
                    mazeGraph.addEdge(end, begin);
                }
            }
        }
        
        //connect the edges horizontally
        for(int j = 0; j < 10; j++){
            //connects column 9 with column 8 horizontally 
            if(mazeRooms[8][j] == '-' && mazeRooms[9][j] == '-'){
                //add vertices
                mazeGraph.addVertex(8 + "" + j);
                mazeGraph.addVertex(9 + "" + j);
                //add double edges because its a directed graph
                mazeGraph.addEdge(8 + "" + j, 9 + "" + j);
                mazeGraph.addEdge(9 + "" + j, 8 + "" + j);
            }
            //check for start labels
            if((mazeRooms[8][j] == '-' && mazeRooms[9][j] == 'S') || (mazeRooms[8][j] == 'S' && mazeRooms[9][j] == '-')){
                String begin = 8 + "" + j;
                String end =  9 + "" + j;
                //record start labels
                if(mazeRooms[8][j] == '-' && mazeRooms[9][j] == 'S'){
                    this.startLabel = end;
                }
                else if(mazeRooms[8][j] == 'S' && mazeRooms[9][j] == '-'){
                    this.startLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            if((mazeRooms[8][j] == '-' && mazeRooms[9][j] == 'G') || (mazeRooms[8][j] == 'G' && mazeRooms[9][j] == '-')){
                String begin = 8 + "" + j;
                String end =  9 + "" + j;
                //record start labels
                if(mazeRooms[8][j] == '-' && mazeRooms[9][j] == 'G'){
                    this.endLabel = end;
                }
                else if(mazeRooms[8][j] == 'G' && mazeRooms[9][j] == '-'){
                    this.endLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            //connects column 9 vertically
            if(mazeRooms[9][j] == '-' && mazeRooms[9][j + 1] == '-'){
                //add vertices
                mazeGraph.addVertex(9 + "" + j);
                mazeGraph.addVertex(9 + "" + (j + 1));
                //add double edges because its a directed graph
                mazeGraph.addEdge(9 + "" + j, 9 + "" + (j + 1));
                mazeGraph.addEdge( 9 + "" + (j + 1), 9 + "" + j);
            }
            //check for start labels
            if((mazeRooms[9][j] == '-' && mazeRooms[9][j + 1] == 'S') || (mazeRooms[9][j] == 'S' && mazeRooms[9][j + 1] == '-')){
                String begin = 9 + "" + j;
                String end =  9 + "" + (j + 1);
                //record start labels
                if(mazeRooms[9][j] == '-' && mazeRooms[9][j + 1] == 'S'){
                    this.startLabel = end;
                }
                else if(mazeRooms[9][j] == 'S' && mazeRooms[9][j + 1] == '-'){
                    this.startLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            if((mazeRooms[9][j] == '-' && mazeRooms[9][j + 1] == 'G') || (mazeRooms[9][j] == 'G' && mazeRooms[9][j + 1] == '-')){
                String begin = 9 + "" + j;
                String end =  9 + "" + (j + 1);
                //record start labels
                if(mazeRooms[9][j] == '-' && mazeRooms[9][j + 1] == 'G'){
                    this.endLabel = end;
                }
                else if(mazeRooms[9][j] == 'G' && mazeRooms[9][j + 1] == '-'){
                    this.endLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            //connects row 9 with row 8 vertically 
            if(mazeRooms[j][9] == '-' && mazeRooms[j][8] == '-'){
                //add vertices
                mazeGraph.addVertex(j + "" + 9);
                mazeGraph.addVertex(j + "" + 8);
                //add double edges because its a directed graph
                mazeGraph.addEdge(j + "" + 9, j + "" + 8);
                mazeGraph.addEdge(j + "" + 8, j + "" + 9);
            }
            if((mazeRooms[j][9] == '-' && mazeRooms[j][8] == 'S') || (mazeRooms[j][9] == 'S' && mazeRooms[j][8] == '-')){
                String begin = j + "" + 9;
                String end =  j + "" + 8;
                //record start labels
                if(mazeRooms[j][9] == '-' && mazeRooms[j][8] == 'S'){
                    this.startLabel = end;
                }
                else if(mazeRooms[j][9] == 'S' && mazeRooms[j][8] == '-'){
                    this.startLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            if((mazeRooms[j][9] == '-' && mazeRooms[j][8] == 'G') || (mazeRooms[j][9] == 'G' && mazeRooms[j][8] == '-')){
                String begin = j + "" + 9;
                String end =  j + "" + 8;
                //record start labels
                if(mazeRooms[j][9] == '-' && mazeRooms[j][8] == 'G'){
                    this.endLabel = end;
                }
                else if(mazeRooms[j][9] == 'G' && mazeRooms[j][8] == '-'){
                    this.endLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            //connects row 9 horizontally
            if(mazeRooms[j][9] == '-' && mazeRooms[j + 1][9] == '-'){
                //add vertices
                mazeGraph.addVertex(j + "" + 9);
                mazeGraph.addVertex((j + 1) + "" + 9);
                //add double edges because its a directed graph
                mazeGraph.addEdge(j + "" + 9, (j + 1) + "" + 9);
                mazeGraph.addEdge((j + 1) + "" + 9, j + "" + 9);
            }
            //check for start labels
            if((mazeRooms[j][9] == '-' && mazeRooms[j + 1][9] == 'S') || (mazeRooms[j][9] == 'S' && mazeRooms[j + 1][9] == '-')){
                String begin = j + "" + 9;
                String end =  (j + 1) + "" + 9;
                //record start labels
                if(mazeRooms[j][9] == '-' && mazeRooms[j + 1][9] == 'S'){
                    this.startLabel = end;
                }
                else if(mazeRooms[j][9] == 'S' && mazeRooms[j + 1][9] == '-'){
                    this.startLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
            //check for end labels 
            if((mazeRooms[j][9] == '-' && mazeRooms[j + 1][9] == 'G') || (mazeRooms[j][9] == 'G' && mazeRooms[j + 1][9] == '-')){
                String begin = j + "" + 9;
                String end =  (j + 1) + "" + 9;
                //record start labels
                if(mazeRooms[j][9] == '-' && mazeRooms[j + 1][9] == 'G'){
                    this.endLabel = end;
                }
                else if(mazeRooms[j][9] == 'G' && mazeRooms[j + 1][9] == '-'){
                    this.endLabel = begin;
                }
                //add vertices
                mazeGraph.addVertex(begin);
                mazeGraph.addVertex(end);
                //add doubles edges because its a directed graph
                mazeGraph.addEdge(begin, end);
                mazeGraph.addEdge(end, begin);
            }
        }
    }
    /**
     * This method returns a mazeGraph.
     * 
     * @return DirectedGraph<String>
     */
    public DirectedGraph<String> getMazeGraph(){
        return this.mazeGraph;
    }
    /**
     * 
     */
    public void findPath(){
        LinkedList path = mazeGraph.getPath(getStartLabel(), getEndLabel());
        if(path == null || path.getLength() == 0){
            System.out.println("A path cannot be found from the start to the goal.");
            return;
        }
        else{
            for(int i = 2; i < path.getLength() - 1; i++){//starts at 1 and ends at length - 1 so the start label and the end label are not changed.
                String room = path.getEntry(i).toString();
                int col = Integer.parseInt(room.substring(0, 1));//takes the first char to be the column
                int row = Integer.parseInt(room.substring(1, 2));//takes the second char to be the row
                this.mazeRooms[col][row] = 'O';//sets each room to be part of the path
            }
            String pathString = "";
            for(int i = 1; i < path.getLength(); i++){//constructs the path
                pathString = pathString + path.getEntry(i).toString() + "->";
            }
            System.out.println(pathString.substring(0, pathString.length() - 2));
        }
    }
    /**
     * This method checks if the maze is empty
     * 
     * @return boolean. true if empty, false otherwise.
     */
    public boolean isEmpty(){
        if(MAZE_SIZE == 0){
            return true;
        }
        return false;
    }
    /**
     * This overrides the toString method and returns a string representing the maze
     * 
     * @return String
     */
    @Override
    public String toString(){
        String maze = "";
        for(int i = 0; i < 10; i++){//row
            if(i == 0){
                maze = maze + "+---+---+---+---+---+---+---+---+---+---+---+\n";
                maze = maze + "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n";
                maze = maze + "+---+---+---+---+---+---+---+---+---+---+---+\n";
            }
            maze = maze + "| " + i + " |";
            for(int j = 0; j < 10; j++){//col
                maze = maze + " " + mazeRooms[j][i] + " " + "|";
            }
            maze = maze + "\n";
            maze = maze + "+---+---+---+---+---+---+---+---+---+---+---+\n";
        }
        return maze;
    }
    /**
     * This method sets the start label to a char representing the corresponsing maze room
     * 
     * @param c char
     */
    public void setStartLabel(String c){
        this.startLabel = c;
    }
    
    /**
     * This method returns the maze room of the start
     * 
     * @return String 
     */
    public String getStartLabel(){
        return this.startLabel;
    }
    /**
     * This method sets the end label to a char representing the corresponding maze room
     * 
     * @param c char
     */
    public void setEndLabel(String c){
        this.endLabel = c;
    }
    /**
     * This method returns the maze room of the end
     * 
     * @return String 
     */
    public String getEndLabel(){
        return this.endLabel;
    }
    
}
