package danek.apps.drawutils;

import java.awt.Color;

public final class StdDrawUtils {
	
	//Mohl bych použít mapu, ale protože klíče stejnak budou integers (kterej hráč hraje), tak stačí pole
	public static Color[] barvy = new Color[]{StdDraw.RED, StdDraw.BLUE};
	
	/**
	 * Vykreslí do okna mřížku
	 * @param rows Počet řádků mřížky
	 * @param cols Počet sloupců mřížky
	 * @param colour Barva mřížky
	 */
	public static void Grid(int rows, int cols, Color colour) {
		Color currentlyUsedColour = StdDraw.getPenColor();
		StdDraw.setPenColor(colour);
		
		double partition = (double) 1 / rows;
        
        for (int i = 0; i < rows; i++) {
        	StdDraw.line(0, partition * i, 1, partition * i);
		}
        
        partition = (double) 1 / cols;
        
        for (int i = 0; i < cols; i++) {
        	StdDraw.line(partition * i, 0, partition * i, 1);
		}
        
        StdDraw.setPenColor(currentlyUsedColour);
	}
	
	public static void Grid(int rows, int cols) {
		Grid(rows, cols, StdDraw.getPenColor());
	}
	
	public static int getGridCellRow(int rows, double x) {		
		double partition = (double) 1 / rows;
		
		return rows - 1 - (int) (x / partition);
	}
	
	public static int getGridCellCol(int cols, double y) {		
		double partition = (double) 1 / cols;
			
		return (int) (y / partition);
	}
	
	public static int[] getGridMouseCoords(int rows, int cols) {
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();

		int row = StdDrawUtils.getGridCellRow(rows, y);
		int col = StdDrawUtils.getGridCellCol(cols, x);
		
		return new int[]{row, col};
	}
	
	public static void DrawPlayerSymbol(int player, double x, double y, double rows, double cols) {
		//Nalezení středu buňky
		double Sy = (double) (x + 0.5) / rows;
		double Sx = (double) (y + 0.5) / cols;
		
		if (player == 1) StdDrawUtils.DrawX(Sx, 1 - Sy, rows, cols, player - 1);
		else StdDrawUtils.DrawCircle(Sx, 1 - Sy, rows, cols, player - 1);
	}
	
	public static void DrawCircle(double x, double y, double rows, double cols, int player) {
		//Hodnoty pro resetování
		Color currentlyUsedColour = StdDraw.getPenColor();
		double radius = StdDraw.getPenRadius();
		double partitionX = 0.5 / rows;
		double partitionY = 0.5 / cols;
				
		//Nastavení hodnot pro metodu
		StdDraw.setPenColor(barvy[player]);
		StdDraw.setPenRadius(partitionX * partitionY);
		
		//Vykreslení
		StdDraw.ellipse(x, y, partitionY * 0.9, partitionX * 0.9);
		
		//Obnovení původních hodnot
		StdDraw.setPenColor(currentlyUsedColour);
		StdDraw.setPenRadius(radius);
	}

	public static void DrawX(double x, double y, double rows, double cols, int player) {
		//Hodnoty pro resetování
		Color currentlyUsedColour = StdDraw.getPenColor();
		double radius = StdDraw.getPenRadius();
		double partitionX = 0.4 / rows;
		double partitionY = 0.4 / cols;
		
		//Nastavení hodnot pro metodu
		StdDraw.setPenColor(barvy[player]);
		StdDraw.setPenRadius(partitionX * partitionY * 1.5);
		
		//Vykreslení
		StdDraw.polygon(new double[]{x - partitionY, x + partitionY}, new double[]{y - partitionX, y + partitionX});
		StdDraw.polygon(new double[]{x + partitionY, x - partitionY}, new double[]{y - partitionX, y + partitionX});
		
		//Obnovení původních hodnot
		StdDraw.setPenColor(currentlyUsedColour);
		StdDraw.setPenRadius(radius);
	}
}
