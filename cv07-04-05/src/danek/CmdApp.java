package danek;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class CmdApp {
	public static final Scanner sc = new Scanner(System.in);
	public static CommandLine CMD;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CMD = new CommandLine();
		
        String vstup;
        File soubor;
        Path cesta;
        
        String hostname;
        try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			hostname = System.getenv("USERDOMAIN");
		}
		
        do {
            System.out.println("");
            System.out.print(String.format("%s@%s ~ $ ", System.getProperty("user.name"), hostname));
            vstup = sc.nextLine().trim();
            
            //Pokud je vstup exit, tak se ukončí program.
            if (vstup.equalsIgnoreCase("exit")) continue;
            
            String[] tokens = vstup.split("\s+");
            
            String command = tokens[0];
            String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
            
            switch (command) {
				case "help" -> CMD.help();
				case "dir" -> obsluzDir(arguments);
				case "cd" -> obsluzCd(arguments);
				case "mkfile" -> obsluzMkFile(arguments);
				
				
				default ->
				throw new IllegalArgumentException("Unexpected value: " + command);
			}
            
        } while (vstup.compareToIgnoreCase("exit") != 0);
	}
	
	public static void obsluzDir(String[] args) {
		try {
			System.out.println(args.length == 0 ? CMD.dir() : CMD.dir(Path.of(args[0])));
		}
		catch (InvalidPathException e) {
			System.out.println("Cestu nebylo možné přeložit.");
		}
		catch (NoSuchFileException e) {
			System.out.println("Adresář neexistuje.");
		}
		catch (IOException e) {
			System.out.println("Při čtení adresáře došlo k chybě.");
		}
	}
	
	public static void obsluzCd(String[] args) {
		try {
			CMD.cd(Path.of(args[0]));
		}
		catch (InvalidPathException e) {
			System.out.println("Cestu nebylo možné přeložit.");
		}
		catch (NotDirectoryException e) {
			System.out.println("Zadaná cesta není cesta k adresáři");
		}
	}
	
	public static void obsluzMkFile(String[] args) {
		try {
			CMD.mkfile(Path.of(args[0]));
		}
		catch (InvalidPathException e) {
			System.out.println("Cestu nebylo možné přeložit.");
		}
		catch (FileAlreadyExistsException e) {
			System.out.println(String.format("Soubour s názvem %s již existuje.", args[0]));
		}
		catch (IOException e) {
			System.out.println("Při vytváření souboru došlo k chybě.");
		}
	}
	
	public static void obsluzMkDir(String[] args) {
		try {
			CMD.mkdir(Path.of(args[0]));
		}
		catch (InvalidPathException e) {
			System.out.println("Cestu nebylo možné přeložit.");
		}
		catch (FileAlreadyExistsException e) {
			System.out.println(String.format("Adresář s názvem %s již existuje.", args[0]));
		}
		catch (IOException e) {
			System.out.println("Při vytváření adresáře došlo k chybě.");
		}
	}
	
	public static void obsluzMkDirs(String[] args) {
		try {
			CMD.mkdirs(Path.of(args[0]));
		}
		catch (InvalidPathException e) {
			System.out.println("Cestu nebylo možné přeložit.");
		}
		catch (FileAlreadyExistsException e) {
			System.out.println(String.format("Adresář s cestou %s již existuje.", args[0]));
		}
		catch (IOException e) {
			System.out.println("Při vytváření adresáře došlo k chybě.");
		}
	}
	

}
