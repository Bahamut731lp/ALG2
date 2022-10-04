package danek;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class TextSouborTools {
	
	/**
	 * Metoda otevře dialog pro výběr souboru k analýze
	 * @return Instanci souboru
	 */
	public static File openFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		File selected = null;
		
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileFilter(filter);
		
		int result = fileChooser.showOpenDialog(fileChooser);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			selected = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selected.getAbsolutePath());
		}
		
		return selected;
	}
	
}
