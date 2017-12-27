package camelot;


public class Piece {

	private int color;
	public static final int COLOR_WHITE = 0;
	public static final int COLOR_BLACK = 1;
	
	private int type;
	
	public static final int TYPE_PAWN = 6;
	
	//Chess is played on a square board of
	//eight rows (called ranks and denoted with numbers 1 to 8)
	//and eight columns (called files and denoted with letters a to h) of squares.
	private int row;
	
	public static final int ROW_1 = 0;
	public static final int ROW_2 = 1;
	public static final int ROW_3 = 2;
	public static final int ROW_4 = 3;
	public static final int ROW_5 = 4;
	public static final int ROW_6 = 5;
	public static final int ROW_7 = 6;
	public static final int ROW_8 = 7;
	public static final int ROW_9 = 8;
	public static final int ROW_10 = 9;
	public static final int ROW_11 = 10;
	public static final int ROW_12 = 11;
	public static final int ROW_13 = 12;
	public static final int ROW_14 = 13;
	
	private int column;
	
	public static final int COLUMN_A = 0;
	public static final int COLUMN_B = 1;
	public static final int COLUMN_C = 2;
	public static final int COLUMN_D = 3;
	public static final int COLUMN_E = 4;
	public static final int COLUMN_F = 5;
	public static final int COLUMN_G = 6;
	public static final int COLUMN_H = 7;
	
	private boolean isCaptured = false;

	public Piece(int color, int type, int row, int column) {
		this.row = row;
		this.column = column;
		this.color = color;
		this.type = type;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		String strColor = getColorString(this.color);
		
		String strType = getTypeString(this.type);
		
		String strRow = getRowString(this.row);
		String strColumn = getColumnString(this.column);
		
		return strColor+" "+strType+" "+strRow+"/"+strColumn;
	}

	public int getType() {
		return this.type;
	}
	
	public static String getRowString(int row){
		String strRow = "unknown";
		switch (row) {
			case ROW_1: strRow = "1";break;
			case ROW_2: strRow = "2";break;
			case ROW_3: strRow = "3";break;
			case ROW_4: strRow = "4";break;
			case ROW_5: strRow = "5";break;
			case ROW_6: strRow = "6";break;
			case ROW_7: strRow = "7";break;
			case ROW_8: strRow = "8";break;
			case ROW_9: strRow = "9";break;
			case ROW_10: strRow = "10";break;
			case ROW_11: strRow = "11";break;
			case ROW_12: strRow = "12";break;
			case ROW_13: strRow = "13";break;
			case ROW_14: strRow = "14";break;
		}
		return strRow;
	}
	
	public static String getColumnString(int column){
		String strColumn = "unknown";
		switch (column) {
			case COLUMN_A: strColumn = "A";break;
			case COLUMN_B: strColumn = "B";break;
			case COLUMN_C: strColumn = "C";break;
			case COLUMN_D: strColumn = "D";break;
			case COLUMN_E: strColumn = "E";break;
			case COLUMN_F: strColumn = "F";break;
			case COLUMN_G: strColumn = "G";break;
			case COLUMN_H: strColumn = "H";break;
		}
		return strColumn;
	}
	
	public static String getTypeString(int type){
		switch (type) {
		
			case TYPE_PAWN: return "P";
			
			default: return "?";
		}
	}
	
	public static String getColorString(int color){
		switch (color) {
			case COLOR_BLACK: return "B";
			case COLOR_WHITE: return "W";
			default: return "?";
		}
	}

	public void isCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}

	public boolean isCaptured() {
		return this.isCaptured;
	}

}
