/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class SBMainSetup.
 */
public class SBMainSetup {
	
	/** The _setups. */
	private List<ISetup> _setups = new ArrayList<>();
	
	/** The exit. */
	public boolean EXIT = false;
	
	/**
	 * Instantiates a new SB main setup.
	 */
	public SBMainSetup(){
		_setups.add(new MainContainer());
		_setups.add(new Room001());
	}
	
	/**
	 * Prints the menu.
	 */
	public void printMenu(){
        
        System.out.println("Select one option :");        
        for (int i = 0; i < _setups.size(); i++) {
        	ISetup iSetup = _setups.get(i); 
        	System.out.println(i+" - "+iSetup.getDescription()+"\t"+iSetup.getName());
		}
        System.out.println(_setups.size()+" - Deploy G4 S.M.A.");
        System.out.println((_setups.size()+1)+" - Quit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.close();
        if(choice < _setups.size()){
        	_setups.get(choice).setup();
        	this.EXIT = true;
        } else if(choice == _setups.size()) {
        	for (ISetup iSetup : _setups) {
				iSetup.setup();
			}
        	this.EXIT = true;
        } else if (choice > _setups.size()) {
        	this.EXIT = true;
        }
	}
	
	/**
	 * Launch by index.
	 *
	 * @param index the index
	 */
	public void launchByIndex(int index){
		if(_setups.size() > index){
			System.out.println("CMD Launch : "+_setups.get(index).getName());
			_setups.get(index).setup();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		SBMainSetup mainSetup = new SBMainSetup();
		
		if(args.length > 0){
			int choice = Integer.parseInt(args[0]);
			mainSetup.launchByIndex(choice);
		} else {
			while(!mainSetup.EXIT) {
				mainSetup.printMenu();
			}
		}		
	}
}
