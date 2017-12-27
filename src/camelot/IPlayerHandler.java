package camelot;

public interface IPlayerHandler {
	public Move getMove();
	
	public int getColor();

	public void moveSuccessfullyExecuted(Move move);

}
