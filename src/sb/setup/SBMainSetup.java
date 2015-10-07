package sb.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SBMainSetup {
	
	private List<ISetup> _setups = new ArrayList<>();
	public boolean EXIT = false;
	
	public SBMainSetup(){
		_setups.add(new MainContainer());
		_setups.add(new Room001());
	}
	
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
        } else if(choice == _setups.size()) {
        	for (ISetup iSetup : _setups) {
				iSetup.setup();
			}
        } else if (choice > _setups.size()) {
        	this.EXIT = true;
        }
	}
	
	public void launchByIndex(int index){
		if(_setups.size() > index){
			System.out.println("CMD Launch : "+_setups.get(index).getName());
			_setups.get(index).setup();
		}
	}
	
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
