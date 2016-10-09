/**
 * 
 */
package server;

/**
 * @author chance
 *
 */
public class ServerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String detail = "美国民主党籍政治家，也是第44任美国总统，于2008年初次当选，并于2012年成功连任。";
		Candidate obama = new Candidate("Obama", 55, "男", detail);
	
		new ServerFrame(obama);
	}

}
