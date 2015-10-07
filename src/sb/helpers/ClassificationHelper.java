package sb.helpers;

public class ClassificationHelper {
	/**
	 * Get Classification code of given infos.
	 * @param category
	 * @param type
	 * @param number
	 * @return
	 */
	public static String getClassifcationCode(ECategoryHelper category, ETypeHelper type, int number){
		return new String(category+"-"+type+"-"+String.format("%03d", number));		
	}
	
	public static String getCategoryCode(ECategoryHelper category, ETypeHelper type){
		return new String(category+"-"+type);
	}
}
