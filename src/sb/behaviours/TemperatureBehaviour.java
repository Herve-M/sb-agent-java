package sb.behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import sb.behaviours.fsm.FSMAirConditionerGetter;
import sb.behaviours.fsm.FSMHeatingGetter;
import sb.behaviours.fsm.FSMSMAStateGetter;
import sb.behaviours.fsm.FSMTemperatureGetter;
import sb.behaviours.fsm.NullBehaviour;
import sb.behaviours.manual.UserMSGSender;
import sb.helpers.ClassificationHelper;
import sb.helpers.EAction;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.helpers.EUserAction;
import sb.sensors.AirConditionerMSGSender;
import sb.sensors.HeatingMSGSender;

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
	private static final String ST13 = "S-HT-GET-13";
	private static final String ST14 = "S-HTOFF-SET-14";
	private static final String ST15 = "S-AC-GET-15";
	private static final String ST16 = "S-ACOFF-SET-16";
	private static final String ST17 = "S-AC-GET-17";
	private static final String ST18 = "S-ACOFF-SET-18";
	private static final String ST19 = "S-SMA-GET-19";
	private static final String ST20 = "S-HTP1-SET-20";
	private static final String ST21 = "S-HTON-SET-21";
	
	public TemperatureBehaviour(Agent a, int roomId){
		super(a);
		this.registerFirstState(new FSMTemperatureGetter(roomId) , STBEGIN); // 0
		this.registerLastState(new EndFSMBehavior(), STEND); // 4
		this.registerState(new FSMSMAStateGetter(roomId), ST1); // 1
		
		this.registerState(new FSMHeatingGetter(ClassificationHelper.getClassificationCode(ECategoryHelper.ACTIONER, ETypeHelper.HEATING, 1)), ST2); //GET
		this.registerState(new UserMSGSender(myAgent, EUserAction.HTOFF, roomId), ST3); //SET
		this.registerState(new UserMSGSender(myAgent, EUserAction.ACON, roomId), ST5); //SET
		this.registerState(new FSMHeatingGetter(ClassificationHelper.getClassificationCode(ECategoryHelper.ACTIONER, ETypeHelper.HEATING, 1)), ST6); //GET
		this.registerState(new FSMTemperatureGetter(roomId), ST7); //GET
		this.registerState(new HeatingMSGSender(myAgent, EAction.M1, roomId), ST8); //SET
		this.registerState(new HeatingMSGSender(myAgent, EAction.OFF, roomId), ST9); //SET
		this.registerState(new FSMTemperatureGetter(roomId), ST10);  //GET
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.ON, roomId), ST11); //SET
		this.registerState(new NullBehaviour() , ST12);
		
		this.registerState(new NullBehaviour() , ST13); //Only Exia Scenario
//		this.registerState(new FSMHeatingGetter(ClassificationHelper.getClassifcationCode(ECategoryHelper.ACTIONER, ETypeHelper.HEATING, 1)) , ST13);
		
		this.registerState(new HeatingMSGSender(myAgent, EAction.OFF, roomId), ST14);  //SET
		this.registerState(new FSMAirConditionerGetter(ClassificationHelper.getClassificationCode(ECategoryHelper.ACTIONER, ETypeHelper.AIRCONDITIONER, 1)), ST15); //GET
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.OFF, roomId), ST16); //SET
		
		this.registerState(new FSMAirConditionerGetter(ClassificationHelper.getClassificationCode(ECategoryHelper.ACTIONER, ETypeHelper.AIRCONDITIONER, 1)), ST17); //GET
		this.registerState(new AirConditionerMSGSender(myAgent, EAction.OFF, roomId), ST18); //SET
		this.registerState(new FSMSMAStateGetter(roomId), ST19); //GET
		this.registerState(new HeatingMSGSender(myAgent, EAction.P1, roomId), ST20); //SET
		this.registerState(new UserMSGSender(myAgent, EUserAction.HTON, roomId), ST21); //SET
		
		{ //T>max
			//TEMP
			this.registerTransition(STBEGIN, ST1, 1);
			this.registerTransition(STBEGIN, ST13, 0);
			this.registerTransition(STBEGIN, ST17, -1);
			
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
			
			this.registerDefaultTransition(ST13, STEND);
			
			//Only our SMA, not Exia Scenario
//			this.registerTransition(ST13, ST14,1);
//			this.registerTransition(ST13, ST15,0);
//			this.registerDefaultTransition(ST14, ST15);
//			
//			this.registerTransition(ST15, ST16, 1);
//			this.registerTransition(ST15, STEND, 0);
//			this.registerDefaultTransition(ST16, STEND);
		} // End T[]
		
		{ // T<min
			//AC
			this.registerTransition(ST17, ST18, 1); //On
			this.registerTransition(ST17, ST19, 0); //Off	
			
			//MOD
			this.registerTransition(ST19, ST21, 0); //Manual
			this.registerTransition(ST19, ST20, 1); //Automatic
			
			//End
			this.registerDefaultTransition(ST18, STEND);
			this.registerDefaultTransition(ST21, STEND);
			this.registerDefaultTransition(ST20, STEND);
		} // End T<min
		
	}
	
	@Override
	public int onEnd() {
		myAgent.addBehaviour(new TemperatureBehaviour(myAgent, 1));
		return 1;
	}
}