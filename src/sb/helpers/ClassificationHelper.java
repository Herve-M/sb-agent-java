/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.helpers;


/**
 * The Class ClassificationHelper.
 */
public class ClassificationHelper {
	
	/**
	 * Get Classification code of given infos.
	 *
	 * @param category the category
	 * @param type the type
	 * @param number the number
	 * @return the classifcation code
	 */
	public static String getClassifcationCode(ECategoryHelper category, ETypeHelper type, int number){
		return new String(category+(type == ETypeHelper.NONE ? "" : "-"+type)+"-"+String.format("%03d", number));		
	}
	
	/**
	 * Gets the category code.
	 *
	 * @param category the category
	 * @param type the type
	 * @return the category code
	 */
	public static String getCategoryCode(ECategoryHelper category, ETypeHelper type){
		return new String(category+ (type == ETypeHelper.NONE ? "" : "-"+type));
	}
}
