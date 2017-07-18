package Homework05;

public class GameBoardNode {
	static int counter = 0;
    private GameBoard board;
    private boolean isEnd;
    private  Box currentTurn;
    private  Box nextTurn;
    private static Box winner = Box.E;
    private int possibilities = 9; // equivalent to the number of boxes open, (total num of boxes - total moves completed)
    private int numEmpties;
    private GameBoardNode config[];

    private static double oFinalWinProb = 0.0; // = the number of winning leaves / number of total leaves in subtree
    private static double xFinalWinProb = 0.0; // = number of losing leaves / number of total leaves in subtree
    private static double drawFinalWinProb = 0.0; // =  number of draw leaves / number of total leaves in subtree
    private static double xWinProb = 0.0;
    private static double oWinProb = 0.0;
    private static double noWinProb = 0.0;

    //The probability for x winning is (x/(x+o+n)), X win
    //The probability for o winning is (o/(x+o+n)), O win
    //The probability for n winning is (n/(x+o+n)), No win
    
    

    public GameBoardNode(GameBoard b, int numEmpties){
     	board = b;
     	this.numEmpties = numEmpties;
    }
    
    
    public GameBoardNode(GameBoard b, Box cTurn, int slots){
    	
    	counter++;
        board = b;
        currentTurn = cTurn;
        Box nTurn = GameTree.getOpposite(cTurn);
        
        if(slots%2==0){
        	config = returnTraversals(b,Box.O);
        }
        else{
        	config = returnTraversals(b,Box.X);
        }
        
        
        //checks if it is a win or not for cTurn
        for(int i = 0; i < config.length; i++){
        	if( config.length == 1 && !(GameTree.checkWin(config[i], Box.X) && !(GameTree.checkWin(config[i], Box.O)))){
    			noWinProb+=1.0;
        	}
    		else if(GameTree.checkWin(config[i], Box.X)){
    			fillWinningConfig(i);
    			
    			xWinProb+=1.0;
    			surfaceChecker();
    			//System.out.println("X is Winner");
    		}
    		else if(GameTree.checkWin(config[i], Box.O)){
    			
    			fillWinningConfig(i);
    			oWinProb+=1.0;
    			surfaceChecker();
    			//System.out.println("O is Winner");
    		}
    		else{
    			noWinProb+=1.0;
    			surfaceChecker();
    		}
        	
        	surfaceChecker();
        }
        
        //printCurrentConfigChoices();
		
    }
    
    public void printCurrentConfigChoices(){
    	for (int j = 0; j < config.length; j++) {
			System.out.println((j+1)+((((j+1)%100)==1)?("st"):(((((j+1)%100)==2)?("nd"):(((((j+1)%100)==3)?("rd"):("th"))))))+" option -----------------------");
			for (int i = 0; i < 9; i++) {
				System.out.print(((config[j].getBoard().getBox(i)==Box.N)?("E"):(config[j].getBoard().getBox(i))) + "  ");
				if ((i + 1) % 3 == 0) {
					System.out.println();
				}
			}
		}
    }
    
    public int getNumPossibilities(){
    	return config.length;
    }
    
    public void fillWinningConfig(int i){
    	if(config.length>1){
			for(int j = 0; j < config[i].getBoard().getBoxes().length; j++){
				if(config[i].getBoard().getBox(j)==Box.E){
					config[i].getBoard().setBox(Box.N,j); //replaces the empties with non Empty Enum
					//This makes it so program recognizes it as full so no children if early win.
				}
			}
		}
    }
    
    public void surfaceChecker(){
    	if(GameTree.checkWin(this, Box.X)){
    		xFinalWinProb+=10000000.0;
    	}
    	if(GameTree.checkWin(this, Box.O)){
    		oFinalWinProb+=10000000.0;
    	}
    	if(GameTree.checkWin(this, Box.E)){
    		drawFinalWinProb+=10000000.0;
    	}
    }
    
	public GameBoardNode[] returnTraversals(GameBoard b, Box turn) {
		int numEmpties = countEmpties(b, 0);
		GameBoardNode[] traversals = new GameBoardNode[numEmpties];
		GameBoardNode temp;
		if (numEmpties != 0) {
			for (int i = 0, j = 0; i < b.getBoxes().length && j < numEmpties; i++) {
				if (b.getBox(i) == Box.E) {// nothing there
					b.setBox(turn, i);
					temp = new GameBoardNode(new GameBoard(b.getBoxes()),turn, numEmpties - 1);
					b.setBox(Box.E, i);
				} 
				else {// X or O or N

					temp = new GameBoardNode(b, numEmpties);

				}
				traversals[j] = temp;
				j++;

				if (b.getBox(i) != Box.E) {
					j--;
				}

			}
		}

		return traversals;
	}
    
    
    public int countEmpties(GameBoard b, int index){
    	if(index >= b.getBoxes().length){
    		return 0;
    	}
    	else if(b.getBox(index) == Box.E){
    		return countEmpties(b,index+1)+1;
    	}
    	else{
    		return countEmpties(b,index+1);
    	}
    }
    
    public GameBoard getPotentialGameBoard(int i){
    	return config[i].getBoard();
    }
    
    public GameBoardNode getPotentialGameBoardNode(int i){
    	return config[i];
    }
    
    
    public GameBoardNode nextNode(int index){
    	return config[index];
    }

    public static void setProbabilities(){
    	 
    	
    	
    	if(oFinalWinProb > 1.0){
    		System.out.println("Hi");
    		oFinalWinProb = 1.0;
    	}
    	if(xFinalWinProb > 1.0){
    		System.out.println("Hi");
    		xFinalWinProb = 1.0;
    	}
    	if(drawFinalWinProb > 1.0){
    		drawFinalWinProb = 1.0;
    	}
    	
    	
        oFinalWinProb = oWinProb/(xWinProb+oWinProb+noWinProb);
        xFinalWinProb = xWinProb/(xWinProb+oWinProb+noWinProb);
        drawFinalWinProb = noWinProb/(xWinProb+oWinProb+noWinProb);
    }

    public static void resetProbabilities(){
        oFinalWinProb = 0;
        xFinalWinProb = 0;
        drawFinalWinProb =0;
        xWinProb = 0.0;
        oWinProb = 0.0;
        noWinProb = 0.0;
    }

    public GameBoard getBoard(){
        return board;
    }

    public Box getTurn(){
        return currentTurn;
    }
    
    public static double[] getProbsXON(){
    	return new double[]{xFinalWinProb, oFinalWinProb, drawFinalWinProb, xWinProb,oWinProb, noWinProb};
    }
    
}
