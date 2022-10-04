package danek;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLine {
	private Path cwd;
	
	public static void main(String[] args) {
		CommandLine test = new CommandLine();
		
		try {
			test.mkfile(Path.of("test"));
		} catch (InvalidPathException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CommandLine(Path cwd) {
		if (!Files.isDirectory(cwd)) throw new IllegalArgumentException("Zadaný adresář pro inicializaci příkazové řádky musí existovat.");
		
		this.cwd = cwd.toAbsolutePath().normalize();
	}
	
	public CommandLine() {
		this(Path.of(System.getProperty("user.dir")));
	}
	
	public String help() {
		StringBuilder helpString = new StringBuilder();
		
		helpString.append(String.format("Program je jednoduchý interpret příkazů%n%n"));
		helpString.append(String.format("Dostupné příkazy:%n"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "help", "Zobrazí tuto nápovědu"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "dir", "Formátovaný výpis aktuálního adresáře"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "dir <dir>", "Formátovaný výpis adresáře v parametru"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "cd <dir>", "Změna aktuálního adresáře na adresář v parametru"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "mkfile <file>", "Vytvoření souboru s názvem v parametru"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "mkdir <dir>", "Vytvoření adresáře v aktuálním adresáři"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "mkdirs <path>", "Vytvoření adresáře v zadané cestě"));
		helpString.append(String.format("%-2s%-8s\t%-4s%n", " ", "rename <file> <file>", "Přejmenování souboru na jiný soubor"));
		
		return helpString.toString();
	}
	
	public String dir(Path d) throws IOException {
		
		String format = "%-8s %-16s %s%n";
		String str = System.getProperty("line.separator");
		str += String.format(format, "Type", "Last Modified", "Name");
		
		try (Stream<Path> test = Files.list(d)) {
			str = str + test
				.map(path -> path.toFile())
				.map(s -> String.format(
						format,
						s.isDirectory() ? "d" : "f",
						DateFormat.getInstance().format(new Date(s.lastModified())),
						s.getName()
					)
				)
                .collect(Collectors.joining());
		}
	    
	    return str;
	}
	
	public String dir() throws IOException {
		return dir(this.cwd);
	}
	
	public void cd(Path directory) throws NotDirectoryException {
		if (!Files.isDirectory(directory)) throw new NotDirectoryException("Cesta musí být existující adresář");
		
		this.cwd = directory;
	}
	
	public File mkfile(Path soubor) throws FileAlreadyExistsException, IOException {
		File newFile = soubor.toFile();
		if (newFile.exists()) throw new FileAlreadyExistsException(soubor.getFileName().toString(), null, "Nelze vytvořit soubor s názvem, který již existuje");
		
		newFile.createNewFile();
		
		return newFile;
	}
	
	public File mkdir(Path soubor) throws FileAlreadyExistsException, IOException {
		File newFile = soubor.toFile();
		if (newFile.exists()) throw new FileAlreadyExistsException(soubor.getFileName().toString(), null, "Nelze vytvořit adresář s názvem, který již existuje");
		
		newFile.mkdir();
		
		return newFile;
	}
	
	public File mkdirs(Path soubor) throws FileAlreadyExistsException, IOException {
		File newFile = soubor.toFile();
		if (newFile.exists()) throw new FileAlreadyExistsException(soubor.getFileName().toString(), null, "Nelze vytvořit adresář s názvem, který již existuje");
		
		newFile.mkdirs();
		
		return newFile;
	}
	
	public Path getCwd() {
		return this.cwd;
	}
	
}
