package Homework05;

public class GameTree {

    static int[][] winIndices = {   {0,1,2}, //0th sequence
                                    {3,4,5}, //1st sequence
                                    {6,7,8}, //2nd sequence
                                    {0,3,6}, //3rd sequence
                                    {1,4,7}, //4th sequence
                                    {2,5,8}, //5th sequence
                                    {0,4,8}, //6th sequence
                                    {2,4,6}};//7th sequence

    static int winnerSequenceIndex = -1; //you cant have a -1th index, so -1 = no win

    static int numMoves = 0;
    
    static int counter = 0;
    
    private GameBoardNode root;

    private GameBoardNode cursor;
    
    private Box firstPlayer = Box.X;

    public GameTree(){
        root = new GameBoardNode(new GameBoard(), firstPlayer, 9);
        //buildTree(root, firstPlayer,9);
        cursor = root;
    }


    public void makeMove(int position){
    	//GameBoardNode.resetProbabilities();
    	numMoves++;
    	cursor = new GameBoardNode(cursor.getPotentialGameBoard(position),
    			getOpposite(cursor.getTurn()),9-numMoves);
    	root = cursor;
    	//GameBoardNode.setProbabilities();
    }
    
    

    public double[][] getProbabilities(){
    	GameBoardNode temp;
    	double[] tempProbs = new double[3];
    	double[][] probabilities = new double[this.getRoot().getNumPossibilities()][3];
    	for(int i = 0; i < this.getRoot().getNumPossibilities(); i++){
    		GameBoardNode.resetProbabilities();
    		temp = new GameBoardNode(cursor.getPotentialGameBoard(i),
        			getOpposite(cursor.getTurn()),9-numMoves);
    		GameBoardNode.setProbabilities();
    		tempProbs = GameBoardNode.getProbsXON();
    		probabilities[i][0] = tempProbs[0];
    		probabilities[i][1] = tempProbs[1];
    		probabilities[i][2] = tempProbs[2];
    	}
    	
    	
    	
    	return probabilities;
    }
    
    public GameBoardNode getRoot(){
    	return root;
    }
    
    public static boolean checkWin(GameBoardNode node, Box cTurn){
        Box currentTurn = cTurn;
        
        
        
        
        Box[] currentBoard = new Box[9];
        		
        for(int i = 0; i < 9; i ++){
        	if(node.getBoard().getBox(i)==Box.X){

                currentBoard[i] = Box.X;
        	}
        	if(node.getBoard().getBox(i)==Box.O){

                currentBoard[i] = Box.O;
        	}
        	if(node.getBoard().getBox(i)==Box.E){

                currentBoard[i] = Box.E;
        	}
        }
        
//        System.out.println((counter++)+" ------------------");
//    	for(int i = 0; i < currentBoard.length; i++){
//    		System.out.print(currentBoard[i]);
//    		if((i+1)%3==0){
//    			System.out.println("");
//    		}
//    	}
        
        
        int[] letterIndices = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        //Limited to 5 spots because in a 3x3 board, the max moves a player can do is 5.


        int letterCounter = 0;
        //This is the counter for every time currentTurn's letter is detected
        //The final value for this number is equal to the number of moves.

        for(int i = 0; i<currentBoard.length; i++){
            if(currentBoard[i] == currentTurn){
                letterIndices[letterCounter] = i;
                letterCounter++; // the "cursor" for the counter is increased
            }
        }



        boolean sequenceChecker = false;
        int frontNum, midNum, endNum;
        for(int n = 0; n < winIndices.length; n++) {
            frontNum = winIndices[n][0];
            midNum = winIndices[n][1];
            endNum = winIndices[n][2];
            if (contains(letterIndices, frontNum) && contains(letterIndices, midNum) && contains(letterIndices, endNum)) {
                sequenceChecker |= true;
                winnerSequenceIndex = n; //nth sequence
            }
        }
        return sequenceChecker ;
        
    }
    
    
    public static boolean contains(int[] checkThisArray, int forThisValue){
        boolean returnThis = false;
        for(int i = 0; i < checkThisArray.length; i++){
            if(checkThisArray[i] == forThisValue){
                returnThis |= true;
            }
        }
        return returnThis;
    }
    
   
    

    public static Box getOpposite(Box b){
        return (b==Box.X)?(Box.O):(Box.X);
    }
}
