package net.sourceforge.jswarm_pso;

/**
 * General pupose rutines
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class Gpr {

	/** Only print this number of warnings */
	public static int MAX_NUMBER_OF_WARNINGS = 5;

	/** Print warning only N times */
	public static int warnCount = 0;

	/**
	 * Prints a debug message (prints class name, method and line number)
	 * @param b : Boolean to print
	 */
	public static void debug(boolean b) {
		debug(new Boolean(b), 1);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param debug : Only prints message if debug=true  			
	 * @param i : int to print
	 */
	public static void debug(boolean debug, int i) {
		if( debug ) debug(new Integer(i), 1);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param debug : Only prints message if debug=true  			
	 * @param obj : Object to print
	 */
	public static void debug(boolean debug, Object obj) {
		if( debug ) debug(obj, 1);
	}

	/**
	 * Prints a debug message (prints class name, method and line number)
	 * @param d : double to print
	 */
	public static void debug(double d) {
		debug(Double.toString(d), 1);
	}

	/**
	 * Prints a debug message (prints class name, method and line number)
	 * @param i	: int to print
	 */
	public static void debug(int i) {
		debug(new Integer(i), 1);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param currentDebugLevel : Debug level being used
	 * @param thisMessageLevel : Only prints message if thisMessageLevel >= currentDebugLevel  			
	 * @param obj : Object to print
	 */
	public static void debug(int currentDebugLevel, int thisMessageLevel, Object obj) {
		if( currentDebugLevel >= thisMessageLevel ) debug(obj, 1);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param obj : Object to print
	 */
	public static void debug(Object obj) {
		debug(obj, 1, true);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param obj : Object to print
	 * @param offset : Offset N lines from stacktrace
	 */
	public static void debug(Object obj, int offset) {
		debug(obj, offset, true);
	}

	/**
	 * Prits a debug message (prints class name, method and line number)
	 * @param obj : Object to print
	 * @param offset : Offset N lines from stacktrace
	 * @param newLine : Print a newline char at the end ('\n')
	 */
	public static void debug(Object obj, int offset, boolean newLine) {
		StackTraceElement ste = new Exception().getStackTrace()[1 + offset];
		String steStr = ste.getClassName();
		int ind = steStr.lastIndexOf('.');
		steStr = steStr.substring(ind + 1);
		steStr += "." + ste.getMethodName() + "(" + ste.getLineNumber() + "):\t" + (obj == null ? null : obj.toString());
		if( newLine ) System.err.println(steStr);
		else System.err.print(steStr);
	}

	/**
	 * Print a warning message (only a few of them) 
	 * @param warning
	 */
	public static void warn(String warning) {
		if( warnCount < MAX_NUMBER_OF_WARNINGS ) {
			warnCount++;
			Gpr.debug(warning, 2);
		}
	}
}