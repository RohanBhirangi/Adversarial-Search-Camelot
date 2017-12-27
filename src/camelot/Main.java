package camelot;

import camelot.NegamaxAlphaBetaPlayer;
import camelot.NegamaxPlayer;
import camelot.ChessGui;
import camelot.ChessGame;
import camelot.Piece;



public class Main {

	public static void main(String[] args) {			
		ChessGame chessGame = new ChessGame();
		ChessGui chessGui = new ChessGui(chessGame);
		
		NegamaxPlayer negamax = new NegamaxPlayer(chessGame, Piece.COLOR_WHITE, 2);
		NegamaxAlphaBetaPlayer negamaxAlphaBeta = new NegamaxAlphaBetaPlayer(chessGame, Piece.COLOR_BLACK, 2);

		chessGame.setWhitePlayerHandler(negamax);
		chessGame.setBlackPlayerHandler(negamaxAlphaBeta);		
		chessGame.setGraphicUpdator(chessGui);

		new Thread(chessGame).start();
	}
	
}