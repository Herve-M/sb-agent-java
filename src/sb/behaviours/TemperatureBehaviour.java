package sb.behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import sb.equipment.AirConditionerEquipment;
import sb.equipment.EAction;
import sb.equipment.HeatingEquipment;
import sb.interactioners.AirConditionerInterActioner;
import sb.interactioners.HeatingInterActioner;
import sb.sensors.AirConditionerMSGSender;
import sb.sensors.AirConditionerSensors;
import sb.sensors.HeatingMSGSender;
import sb.sensors.HeatingSensors;

class HeatingMSGReceiver extends OneShotBehaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
};

class AirConditionMSGReceiver extends OneShotBehaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
};

class TemperatureMSGReceiver extends OneShotBehaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
};

class SMASetting extends OneShotBehaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
};

class EndFSMBehavior extends OneShotBehaviour {

	@Override
	public void action() {
		System.out.println("!!!! One clycle done !!!!");
		
	}
};

/**
 * Temperature FSM Behavior
 * Use MSG to Get and Set data.
 * @author Hervé
 *
 */
public class TemperatureBehaviour extends FSMBehaviour {
	
	private static final String STBEGIN = "S-TEMP-GET-0";
	private static final String STEND = "S-END";
	private static final String ST1 = "S-SMA-GET-1";
	private static final String ST2 = "S-HT-GET-2";
	private static final String ST3 = "S-HTON-SET-3";
	private static final String ST5 = "S-ACON-SET-5";
	private static final String ST6 = "S-HT-GET-6";
	private static final String ST7 = "S-TEMP-GET-7";
	private static final String ST8 = "S-HTM1-SET-8";
	private static final String ST9 = "S-HTOFF-SET-9";
	private static final String ST10 = "S-TEMP-GET-10";
	private static final String ST11 = "S-ACON-SET-11";
	private static final String ST12 = "S-NULL-12";
	private static final String ST13 = "S-EPS-13";
	private static final String ST14 = "S-HT-GET-14";
	private static final String ST15 = "S-HTOFF-SET-15";
	private static final String ST16 = "S-AC-GET-16";
	private static final String ST17 = "S-ACOFF-SET-17";
	private static final String ST18 = "S-AC-GET-18";
	private static final String ST19 = "S-ACOFF-SET-19";
	private static final String ST20 = "S-SMA-GET-20";
	private static final String ST21 = "S-HTP1-SET-21";
	private static final String ST22 = "S-HTON-SET-22";
	
	public TemperatureBehaviour(Agent a, int roomId){
		this.registerFirstState(new TemperatureMSGReceiver() , STBEGIN); // 0
		this.registerLastState(new EndFSMBehavior(), STEND); // 4
		this.registerState(new SMASetting(), ST1); // 1
		
		this.registerState(new HeatingMSGReceiver(), ST2); //TODO
		this.registerState(new HeatingMSGSender(myAgent, EAction.OFF, roomId), ST3); //SET
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.ON, roomId), ST5); //SET
		this.registerState(new HeatingMSGReceiver(), ST6); //TODO
		this.registerState(new TemperatureMSGReceiver(), ST7); //TODO
		this.registerState(new HeatingMSGSender(myAgent, EAction.M1, roomId), ST8); //SET
		this.registerState(new HeatingMSGSender(myAgent, EAction.OFF, roomId), ST9); //SET
		this.registerState(new TemperatureMSGReceiver(), ST10);  //TODO
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.ON, roomId), ST11); //TODO
		this.registerState(new NullBehaviour() , ST12);
		this.registerState(new NullBehaviour(), ST13);
		this.registerState(new HeatingSensors(myAgent, new HeatingInterActioner("")), ST14); //TODO
		this.registerState(new HeatingMSGSender(myAgent, EAction.OFF, roomId), ST15);  //SET
		this.registerState(new AirConditionerSensors(myAgent, new AirConditionerInterActioner("")), ST16); //TODO
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.OFF, roomId), ST17); //SET
		this.registerState(new AirConditionerSensors(myAgent, new AirConditionerInterActioner("")), ST18); //TODO
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.OFF, roomId), ST19); //SET
		this.registerState(new SMASetting(), ST20); //TODO
		this.registerState(new HeatingMSGSender(myAgent, EAction.P1, roomId), ST21); //SET
		this.registerState(new HeatingMSGSender(myAgent, EAction.ON, roomId), ST22); //SET
		
		{ //T>max
			//TEMP
			this.registerTransition(STBEGIN, ST1, 1);
			this.registerTransition(STBEGIN, ST13, 0);
			this.registerTransition(STBEGIN, ST18, -1);
			
			//MOD
			this.registerTransition(ST1, ST2, 0); //Manual
			this.registerTransition(ST1, ST6, 1); //Automatic
			
			{ //Manual
				//HT
				this.registerTransition(ST2, ST3, 1); //On
				this.registerTransition(ST2, ST5, 0); //Off
				
				//End
				this.registerDefaultTransition(ST3, STEND);
				this.registerDefaultTransition(ST5, STEND);
			}
			{ // Auto
				//HT
				this.registerTransition(ST6, ST7, 1); //On
				this.registerTransition(ST6, ST10, 0); //Off
				
				//TEMP
				this.registerTransition(ST7, ST8, 1);
				this.registerTransition(ST7, ST9, 0);
				this.registerTransition(ST10, ST11, 1);
				this.registerTransition(ST10, ST12, 0);
				
				//End
				this.registerDefaultTransition(ST8, STEND);
				this.registerDefaultTransition(ST9, STEND);
				this.registerDefaultTransition(ST11, STEND);
				this.registerDefaultTransition(ST12, STEND);
			}
		} // End Tmax
		
		{ // T[]
			this.registerDefaultTransition(ST13, ST14);
			this.registerDefaultTransition(ST13, ST16);
			
			//HT
			this.registerTransition(ST14, ST15, 1); //On
			this.registerTransition(ST14, STEND, 0); //Off
			
			//AC
			this.registerTransition(ST16, ST17, 1); //On
			this.registerTransition(ST16, STEND, 0); //Off
			
			//End
			this.registerDefaultTransition(ST15, STEND);
			this.registerDefaultTransition(ST17, STEND);
		} // End T[]
		
		{ // T<min
			//AC
			this.registerTransition(ST18, ST19, 1); //On
			this.registerTransition(ST18, ST20, 0); //Off	
			
			//MOD
			this.registerTransition(ST20, ST22, 0); //Manual
			this.registerTransition(ST20, ST21, 1); //Automatic
			
			//End
			this.registerDefaultTransition(ST19, STEND);
			this.registerDefaultTransition(ST22, STEND);
			this.registerDefaultTransition(ST21, STEND);
		} // End T<min
		
	}
}