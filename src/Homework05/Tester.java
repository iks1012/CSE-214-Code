package Homework05;

public class Tester {
	static GameTree gt = new GameTree();
	public static void main(String[] args){
		
		
		GameBoardNode.setProbabilities();
		double[] probabilities = GameBoardNode.getProbsXON();
		for(int i = 0; i < probabilities.length; i++){
			System.out.println(probabilities[i]);
		}
		
		gt.makeMove(0);
		gt.makeMove(3);
		gt.makeMove(4);
		gt.makeMove(2);
		gt.makeMove(0);
		gt.makeMove(0);
		gt.makeMove(1);
		
		showProbs();
		
		showCurrentBoard();
		
	}
	
	public static void showCurrentBoard(){
		GameBoardNode test = gt.getRoot();
		Box[] tester = test.getBoard().getBoxes();
		
		System.out.println("--------------------");
		for(int i = 0; i<9;i++){
			System.out.print(tester[i]+" ");
			if((i+1)%3==0){
				System.out.println();
			}
		}
	}
	
	public static void showProbs(){
		double[][] probs =  gt.getProbabilities();
		for(int i = 0; i < probs.length; i++){
			
			System.out.println("Option "+(i+1)+": ");
			if(GameTree.checkWin(gt.getRoot().getPotentialGameBoardNode(i), Box.X)){
				probs[i][0] = 1.0;
				probs[i][1] = 0.0;
				probs[i][2] = 0.0;
			}
			else if(GameTree.checkWin(gt.getRoot().getPotentialGameBoardNode(i), Box.O)){
				
				probs[i][0] = 0.0;
				probs[i][1] = 1.0;
				probs[i][2] = 0.0;
			}
			else if (!(GameTree.checkWin(gt.getRoot().getPotentialGameBoardNode(i), Box.X)) && !(GameTree.checkWin(gt.getRoot().getPotentialGameBoardNode(i), Box.O))&&probs.length ==1){
				probs[i][0] = 0.0;
				probs[i][1] = 0.0;
				probs[i][2] = 1.0;
			}
			System.out.println("X prob: "+ probs[i][0]);
			System.out.println("O prob: "+ probs[i][1]);
			System.out.println("Tie prob: "+ probs[i][2]);
		}
	}
}
