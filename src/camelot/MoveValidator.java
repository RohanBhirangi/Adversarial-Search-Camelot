package camelot;
/**
 * reference
 *   a  b  c  d  e  f  g  h  
 *           +--+--+
 *  
 *        +--+--+--+--+
 *  
 *     +--+--+--+--+--+--+
 *    
 *  +--+--+--+--+--+--+--+--+
 * 8|BR|BN|BB|BQ|BK|BB|BN|BR|8
 * +--+--+--+--+--+--+--+--+
 * 7|BP|BP|BP|BP|BP|BP|BP|BP|7
 *  +--+--+--+--+--+--+--+--+
 * ..
 * 2|WP|WP|WP|WP|WP|WP|WP|WP|2
 *  +--+--+--+--+--+--+--+--+
 * 1|WR|WN|WB|WQ|WK|WB|WN|WR|1
 *     +--+--+--+--+--+--+
 *  
 *        +--+--+--+--+        
 *  
 *           +--+--+
 *    
 *   a  b  c  d  e  f  g  h  
 *
 */
public class MoveValidator {

	private ChessGame chessGame;
	private Piece sourcePiece;
	private Piece targetPiece;
	private boolean debug;
	private Piece targetPiece1;
	private Piece targetPiece2;
	private Piece targetPiece3;
	private Piece targetPiece4;
	private Piece targetPiece5;
	private Piece targetPiece6;
	private Piece targetPiece7;
	private Piece targetPiece8;


	public MoveValidator(ChessGame chessGame){
		this.chessGame = chessGame;
	}
	
	/**
	 * Checks if the specified move is valid
	 * @param move to validate
	 * @param debug 
	 * @return true if move is valid, false if move is invalid
	 */
	public boolean isMoveValid(Move move, boolean debug) {
		this.debug = debug;
		int sourceRow = move.sourceRow;
		int sourceColumn = move.sourceColumn;
		int targetRow = move.targetRow;
		int targetColumn = move.targetColumn;
		
		sourcePiece = this.chessGame.getNonCapturedPieceAtLocation(sourceRow, sourceColumn);
		targetPiece = this.chessGame.getNonCapturedPieceAtLocation(targetRow, targetColumn);
		targetPiece1 = this.chessGame.getNonCapturedPieceAtLocation(targetRow-1, targetColumn);
		targetPiece2 = this.chessGame.getNonCapturedPieceAtLocation(targetRow-1, targetColumn-1);
		targetPiece3 = this.chessGame.getNonCapturedPieceAtLocation(targetRow, targetColumn-1);
		targetPiece4 = this.chessGame.getNonCapturedPieceAtLocation(targetRow+1, targetColumn-1);
		targetPiece5 = this.chessGame.getNonCapturedPieceAtLocation(targetRow+1, targetColumn);
		targetPiece6 = this.chessGame.getNonCapturedPieceAtLocation(targetRow+1, targetColumn+1);
		targetPiece7 = this.chessGame.getNonCapturedPieceAtLocation(targetRow, targetColumn+1);
		targetPiece8 = this.chessGame.getNonCapturedPieceAtLocation(targetRow-1, targetColumn+1);
	
		// source piece does not exist
		if( sourcePiece == null ){
			log("source piece does not exist");
			return false;
		}
		
		if(targetRow == 0 && targetColumn == 0){
			
			return false;
		}
		if(targetRow == 0 && targetColumn == 1){
		
			return false;
		}
		if(targetRow == 0 && targetColumn == 2){
			
			return false;
		}
		if(targetRow == 0 && targetColumn == 5){
			
			return false;
		}
		if(targetRow == 0 && targetColumn == 6){
			
			return false;
		}
		if(targetRow == 0 && targetColumn == 7){
			
			return false;
		}
		if(targetRow == 1 && targetColumn == 0){
			
			return false;
		}
		if(targetRow == 1 && targetColumn == 1){
			
			return false;
		}
		if(targetRow == 1 && targetColumn == 6){
		
			return false;
		}
		if(targetRow == 1 && targetColumn == 7){
			return false;
		}
		if(targetRow == 2 && targetColumn == 0){
			return false;
		}
		if(targetRow == 2 && targetColumn == 7){
			return false;
		}
		if(targetRow == 11 && targetColumn == 0){
			return false;
		}
		if(targetRow == 11 && targetColumn == 7){
			return false;
		}
		if(targetRow == 12 && targetColumn == 0){
			return false;
		}
		if(targetRow == 12 && targetColumn == 1){
			return false;
		}
		if(targetRow == 12 && targetColumn == 6){
			return false;
		}
		if(targetRow == 12 && targetColumn == 7){
			return false;
		}
		if(targetRow == 13 && targetColumn == 0){
			return false;
		}
		if(targetRow == 13 && targetColumn == 1){
			return false;
		}
		if(targetRow == 13 && targetColumn == 2){
			return false;
		}
		if(targetRow == 13 && targetColumn == 5){
			return false;
		}
		if(targetRow == 13 && targetColumn == 6){
			return false;
		}
		if(targetRow == 13 && targetColumn == 7){
			return false;
		}
	/*	
		// source piece has right color?
		if( sourcePiece.getColor() == Piece.COLOR_WHITE
				&& this.chessGame.getGameState() == ChessGame.GAME_STATE_WHITE){
			// ok
		}else if( sourcePiece.getColor() == Piece.COLOR_BLACK
				&& this.chessGame.getGameState() == ChessGame.GAME_STATE_BLACK){
			// ok
		}else{
			log("it's not your turn: "
					+"pieceColor="+Piece.getColorString(sourcePiece.getColor())
					+"gameState="+this.chessGame.getGameState());
			ChessConsole.printCurrentGameState(this.chessGame);
			// it's not your turn
			return false;
		}
		*/
		// check if target location within boundaries
		if( targetRow < Piece.ROW_1 || targetRow > Piece.ROW_14
				|| targetColumn < Piece.COLUMN_A || targetColumn > Piece.COLUMN_H){
			//target row or column out of scope
			log("target row or column out of scope");
			return false;
		}
		
		// validate piece movement rules
		boolean validPieceMove = false;
		switch (sourcePiece.getType()) {
			
			case Piece.TYPE_PAWN:
				validPieceMove = isValidPawnMove(sourceRow,sourceColumn,targetRow,targetColumn);break;
			
			default: break;
		}
		
		if( !validPieceMove){
			return false;
		}
		
		else{
			// ok
		}
		
		
		// handle stalemate and checkmate
		// ..
		
		return true;
	}

	private boolean isTargetLocationCaptureable() {
		
		if(targetPiece1 == null || targetPiece2 == null || targetPiece3 == null || targetPiece4 == null || targetPiece5 == null || targetPiece6 == null || targetPiece7 == null || targetPiece8 == null ){
			return false;
		}
		
		 if( targetPiece1.getColor() != sourcePiece.getColor() || targetPiece2.getColor() != sourcePiece.getColor() || targetPiece3.getColor() != sourcePiece.getColor() || targetPiece4.getColor() != sourcePiece.getColor() || targetPiece5.getColor() != sourcePiece.getColor() || targetPiece6.getColor() != sourcePiece.getColor() || targetPiece7.getColor() != sourcePiece.getColor() || targetPiece8.getColor() != sourcePiece.getColor()){
			return true;
		}
		else{
			return false;
		}
	}

	private boolean isTargetLocationFree() {
		return targetPiece == null;
	}



private boolean isValidPawnMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {
		
		boolean isValid = false;
		// The pawn may move forward to the unoccupied square immediately in front
		// of it on the same file, or on its first move it may advance two squares
		// along the same file provided both squares are unoccupied
		if( isTargetLocationFree() ){
		
			
		if (sourceColumn == targetColumn){	
			if( sourceRow+1 == targetRow || sourceRow-1 == targetRow){
			isValid = true;
		}
			else if (sourceRow+2==targetRow){
			if (targetPiece1!=null){
				isValid=true;
			}
		}   
			else if (sourceRow-2==targetRow) {
			if (targetPiece5!=null){
				isValid=true;
			}			
		}
		}
		/*else {
			System.out.println("not moving one up");
			isValid = false;
		}*/
		
	
		
		if (sourceColumn-1 == targetColumn){	
			if(  sourceRow == targetRow || sourceRow+1 == targetRow || sourceRow-1 == targetRow){
			isValid = true;
		}else{
			isValid = false;
		}
		}	

		if (sourceColumn+1 == targetColumn){	
			if(  sourceRow == targetRow || sourceRow+1 == targetRow || sourceRow-1 == targetRow){
			isValid = true;
		}else{
			
			isValid = false;
		}
		}	
		
		if (sourceColumn+2 == targetColumn){
			if (sourceRow+2 == targetRow){
				if (targetPiece2!=null ){
					isValid=true;
				}
			} else if (sourceRow-2 == targetRow){
				if (targetPiece4!=null ){
					isValid=true;
			}
			} else if (sourceRow == targetRow){
				if (targetPiece3 != null){
					isValid=true;
			}
		}
		}
		

		if (sourceColumn-2 == targetColumn){
			if (sourceRow+2 == targetRow){
				if (targetPiece8!=null ){
					isValid=true;
				}
			} else if (sourceRow-2 == targetRow){
				if (targetPiece6!=null ){
					isValid=true;
			}
			} else if (sourceRow == targetRow){
				if (targetPiece7!=null ){
					isValid=true;
			}
		}
		}
		
	
		
		// or it may move
		// to a square occupied by an opponent�s piece, which is diagonally in front
		// of it on an adjacent file, capturing that piece. 
		}else if( isTargetLocationCaptureable() ){
			

//			if (sourceColumn+2 == targetColumn){
//				if (sourceRow+2 == targetRow){
//					if (targetPiece2.getColor() != sourcePiece.getColor()){
//						isValid=true;
//					}
//				} else if (sourceRow-2 == targetRow){
//					if (targetPiece4.getColor() !=sourcePiece.getColor()){
//						isValid=true;
//				}
//				} else if (sourceRow == targetRow){
//					if (targetPiece3.getColor() != sourcePiece.getColor()){
//						isValid=true;
//				}
//			}
//			}
//			
//
//			if (sourceColumn-2 == targetColumn){
//				if (sourceRow+2 == targetRow){
//					if (targetPiece8.getColor() != sourcePiece.getColor()){
//						isValid=true;
//					}
//				} else if (sourceRow-2 == targetRow){
//					if (targetPiece6.getColor() != sourcePiece.getColor()){
//						isValid=true;
//				}
//				} else if (sourceRow == targetRow){
//					if (targetPiece7.getColor() != sourcePiece.getColor()){
//						isValid=true;
//				}
//			}
//			}
//			
//		
//			if (sourceColumn== targetColumn){
//				if (sourceRow+2==targetRow){
//					if (targetPiece1.getColor() != sourcePiece.getColor()){
//						isValid=true;
//					}
//				} else if (sourceRow-2==targetRow) {
//					if (targetPiece5.getColor() != sourcePiece.getColor()){
//						isValid=true;
//					}			
//				}
//			}
			
			
		}
		
		// on its first move it may advance two squares
		// ..
		
		// The pawn has two special
		// moves, the en passant capture, and pawn promotion.
		
		// en passant
		// ..
		return isValid;
	}
	
	public static void main(String[] args) {
		ChessGame ch = new ChessGame();
		MoveValidator mo = new MoveValidator(ch);
		Move move = null;
		boolean isValid;
		
		int sourceRow; int sourceColumn; int targetRow; int targetColumn;
		int testCounter = 1;

		// ok
		sourceRow = Piece.ROW_2; sourceColumn = Piece.COLUMN_D;
		targetRow = Piece.ROW_3; targetColumn = Piece.COLUMN_D;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid( move,true);
		ch.movePiece(move);
		System.out.println(testCounter+". test result: "+(true==isValid));
		testCounter++;
		ch.changeGameState();
		
		// it's not white's turn
		sourceRow = Piece.ROW_2; sourceColumn = Piece.COLUMN_B;
		targetRow = Piece.ROW_3; targetColumn = Piece.COLUMN_B;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid( move, true);
		System.out.println(testCounter+". test result: "+(false==isValid));
		testCounter++;
		
		// ok
		sourceRow = Piece.ROW_7; sourceColumn = Piece.COLUMN_E;
		targetRow = Piece.ROW_6; targetColumn = Piece.COLUMN_E;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid( move, true);
		ch.movePiece( move );
		System.out.println(testCounter+". test result: "+(true==isValid));
		testCounter++;
		ch.changeGameState();
		
		// pieces in the way
		sourceRow = Piece.ROW_1; sourceColumn = Piece.COLUMN_F;
		targetRow = Piece.ROW_4; targetColumn = Piece.COLUMN_C;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid(move, true);
		System.out.println(testCounter+". test result: "+(false==isValid));
		testCounter++;
		
		// ok
		sourceRow = Piece.ROW_1; sourceColumn = Piece.COLUMN_C;
		targetRow = Piece.ROW_4; targetColumn = Piece.COLUMN_F;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid(move, true);
		ch.movePiece(move);
		System.out.println(testCounter+". test result: "+(true==isValid));
		testCounter++;
		ch.changeGameState();
		
		// ok
		sourceRow = Piece.ROW_8; sourceColumn = Piece.COLUMN_B;
		targetRow = Piece.ROW_6; targetColumn = Piece.COLUMN_C;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid( move, true);
		ch.movePiece(move);
		System.out.println(testCounter+". test result: "+(true==isValid));
		testCounter++;
		ch.changeGameState();
		
		// invalid knight move
		sourceRow = Piece.ROW_1; sourceColumn = Piece.COLUMN_G;
		targetRow = Piece.ROW_3; targetColumn = Piece.COLUMN_G;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid( move, true);
		System.out.println(testCounter+". test result: "+(false==isValid));
		testCounter++;
		
		// invalid knight move
		sourceRow = Piece.ROW_1; sourceColumn = Piece.COLUMN_G;
		targetRow = Piece.ROW_2; targetColumn = Piece.COLUMN_E;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid(move, true);
		System.out.println(testCounter+". test result: "+(false==isValid));
		testCounter++;
		
		// ok
		sourceRow = Piece.ROW_1; sourceColumn = Piece.COLUMN_G;
		targetRow = Piece.ROW_3; targetColumn = Piece.COLUMN_H;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid(move, true);
		ch.movePiece(move);
		System.out.println(testCounter+". test result: "+(true==isValid));
		testCounter++;
		ch.changeGameState();

		// pieces in between
		sourceRow = Piece.ROW_8; sourceColumn = Piece.COLUMN_A;
		targetRow = Piece.ROW_5; targetColumn = Piece.COLUMN_A;
		move = new Move(sourceRow, sourceColumn, targetRow, targetColumn);
		isValid = mo.isMoveValid(move, true);
		ch.movePiece(move);
		System.out.println(testCounter+". test result: "+(false==isValid));
		testCounter++;
		
		//ConsoleGui.printCurrentGameState(ch);

	}

	private void log(String message) {
		if(debug) System.out.println(message);
		
	}
	
}
