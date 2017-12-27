package camelot;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//import camelot.console.ChessConsole;
import camelot.ChessGame;
import camelot.IPlayerHandler;
import camelot.Move;
import camelot.MoveValidator;
import camelot.Piece;

public class NegamaxPlayer extends JPanel implements IPlayerHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChessGame chessGame;
	private MoveValidator moveValidator;
	private int color;	
	private int depth;
	
	/**
	 * number of moves to look into the future
	 */
//	public int maxDepth = 3;


	public NegamaxPlayer(ChessGame chessGame, int color, int depth) {
		this.setChessGame(chessGame);
		this.setColor(color);
		this.setDepth(depth);
		this.setMoveValidator(chessGame.getMoveValidator());
	}
	
	public void setChessGame(ChessGame chessGame) {
		this.chessGame = chessGame;
	}
	
	public ChessGame getChessGame() {
		return this.chessGame;
	}
	
	@Override
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public void setMoveValidator(MoveValidator moveValidator) {
		this.moveValidator = moveValidator;
	}
	
	public MoveValidator getMoveValidator() {
		return this.moveValidator;
	}

	@Override
	public Move getMove() {
		return getBestMove();
	}

	/**
	 * get best move for current game situation
	 * @return a valid Move instance
	 */
	private Move getBestMove() {
		System.out.println("getting best move");
//		ChessConsole.printCurrentGameState(this.chessGame);
		System.out.println("thinking...");
		
		List<Move> validMoves = generateMoves(false);
		int bestResult = Integer.MIN_VALUE;
		Move bestMove = null;
		
		for (Move move : validMoves) {
			System.out.println(validMoves);
			executeMove(move);
			System.out.println("evaluate move: " + move + " =========================================");
			int evaluationResult = negamax(this.depth, 1);
			
			undoMove(move);
			if( evaluationResult > bestResult){
				bestResult = evaluationResult;
				bestMove = move;
			}
			
		}
		System.out.println("done thinking! best move is: "+bestMove);
		System.out.println(this.chessGame.getGameState());
		return bestMove;
	}	

	@Override
	public void moveSuccessfullyExecuted(Move move) { 
		System.out.println(color + " executed: " + move);
	}

	/**
	 * evaluate current game state according to nega max algorithm
	 *
	 * @param depth - current depth level (number of counter moves that still need to be evaluated)
	 * @param indent - debug string, that is placed in front of each log message
	 * @return integer score of game state after looking at "depth" counter moves
	 */	
	private int negamax(int depth, int color) {
		if (depth <= 1
				|| this.chessGame.getGameState() == ChessGame.GAME_STATE_END_WHITE_WON
				|| this.chessGame.getGameState() == ChessGame.GAME_STATE_END_BLACK_WON){
				
				return color * evaluateState();
			}
			
			List<Move> moves = generateMoves(false);
			int bestValue = Integer.MIN_VALUE;			
			for(Move currentMove : moves){				
				executeMove(currentMove);
				int v = -1 * negamax(depth - 1, -color);
				bestValue = (bestValue > v) ? bestValue : v;				
				undoMove(currentMove);								
				System.out.println("Move : "+currentMove+" and with best value: "+bestValue);									
			}
			return bestValue;
	}

	/**
	 * undo specified move
	 */
	private void undoMove(Move move) {
		//System.out.println("undoing move");
		this.chessGame.undoMove(move);
		//state.changeGameState();
		this.chessGame.changeGameState();
	}

	/**
	 * Execute specified move. This will also change the game state after the
	 * move has been executed.
	 */
	private void executeMove(Move move) {
		//System.out.println("executing move");
		System.out.println(move);
		this.chessGame.movePiece(move);
		System.out.println("helpme");
		this.chessGame.changeGameState();
	}
	
	/*private void executeMove1(Move move) {
		//System.out.println("executing move");
		System.out.println(move);
		this.chessGame.movePiece(move);
		System.out.println("helpme");
		//this.chessGame.changeGameState();
	}
*/
	/**
	* generate all possible/valid moves for the specified game
	* @param state - game state for which the moves should be generated
	* @return list of all possible/valid moves
	*/
	private List<Move> generateMoves(boolean debug) {

		List<Piece> pieces = this.chessGame.getPieces();
		List<Move> validMoves = new ArrayList<Move>();
		Move testMove = new Move(0,0,0,0);

		// iterate over all non-captured pieces
		for (Piece piece : pieces) {

			// only look at pieces of current players color
			if (piece.getColor() == this.getColor()) {
				// start generating move
				testMove.sourceRow = piece.getRow();
				testMove.sourceColumn = piece.getColumn();

				// iterate over all board rows and columns
				for (int targetRow = Piece.ROW_1; targetRow <= Piece.ROW_14; targetRow++) {
					for (int targetColumn = Piece.COLUMN_A; targetColumn <= Piece.COLUMN_H; targetColumn++) {

						// finish generating move
						testMove.targetRow = targetRow;
						testMove.targetColumn = targetColumn;

						if(debug) System.out.println("testing move: "+testMove);
						
						// check if generated move is valid
						if (this.moveValidator.isMoveValid(testMove, true)) {
							// valid move
							validMoves.add(testMove.clone());
						} else {
							// generated move is invalid, so we skip it
						}
					}
				}

			}
		}
		
		return validMoves;
		
	}

	/**
	 * evaluate the current game state from the view of the
	 * current player. High numbers indicate a better situation for
	 * the current player.
	 *
	 * @return integer score of current game state
	 */
	private int evaluateState() {

		// add up score
		//
		int scoreWhite = 0;
		int scoreBlack = 0;
		for (Piece piece : this.chessGame.getPieces()) {
			if(piece.getColor() == Piece.COLOR_BLACK){
				scoreBlack +=
					getScoreForPieceType(piece.getType());
				scoreBlack +=
					getScoreForPiecePosition(piece, piece.getRow(),piece.getColumn());
			}else if( piece.getColor() == Piece.COLOR_WHITE){
				scoreWhite +=
					getScoreForPieceType(piece.getType());
				scoreWhite +=
					getScoreForPiecePosition(piece, piece.getRow(),piece.getColumn());
			}else{
				throw new IllegalStateException(
						"unknown piece color found: "+piece.getColor());
			}
		}
		
		// return evaluation result depending on who's turn it is
		int gameState = this.chessGame.getGameState();
		
		if( gameState == ChessGame.GAME_STATE_BLACK){
			return scoreBlack - scoreWhite;
		
		}else if(gameState == ChessGame.GAME_STATE_WHITE){
			return scoreWhite - scoreBlack;
		
		}else if(gameState == ChessGame.GAME_STATE_END_WHITE_WON
				|| gameState == ChessGame.GAME_STATE_END_BLACK_WON){
			return Integer.MIN_VALUE + 1;
		
		}else{
			throw new IllegalStateException("unknown game state: "+gameState);
		}
	}
	
	/**
	 * get the evaluation bonus for the specified position
	 * @param row - one of Piece.ROW_..
	 * @param column - one of Piece.COLUMN_..
	 * @return integer score
	 */
	private int getScoreForPiecePosition(Piece piece, int row, int column) {
		if(piece.getColor() == Piece.COLOR_WHITE) {
			 return (14 - row);
		} else {
			return (row - 0);
		}
	}

	/**
	 * get the evaluation score for the specified piece type
	 * @param type - one of Piece.TYPE_..
	 * @return integer score
	 */
	private int getScoreForPieceType(int type){
		switch (type) {
		
			case Piece.TYPE_PAWN: return 1;
			
			default: throw new IllegalArgumentException("unknown piece type: "+type);
		}
	}
}
