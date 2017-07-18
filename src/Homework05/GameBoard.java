package Homework05;



public class GameBoard {
    private Box[] board;
    private final int boardSize = 9;

    public GameBoard(){
        board = new Box[boardSize];
        for(int i = 0; i < boardSize; i++){
            board[i] = Box.E;
        }
    }
    
    public GameBoard(Box[] b){
    	board = new Box[b.length];
    	for(int i = 0; i<b.length;i++){
    		board[i] = b[i];
    	}
    }
    
    public Box[] getBoxes(){
    	
        return board;
    }

    public void setBox(Box box, int index){
        board[index] = box;
    }

    public Box getBox(int index){
        return board[index];
    }





}