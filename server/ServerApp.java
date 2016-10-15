/**
 * 
 */
package server;

import java.util.ArrayList;

import candidate.Candidate;


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
		String obamaDetail = "美国民主党籍政治家。";
		Candidate obama = new Candidate(0, "Obama", 55, "男", obamaDetail);
		String xiDetail = "中国共产党第五代领导人";
		Candidate xi = new Candidate(1, "习大大", 63, "男", xiDetail);
		String jiangDetail = "+1s";
		Candidate jiang = new Candidate(2, "长者", 90, "男", jiangDetail);
	
		ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
		candidateList.add(obama);
		candidateList.add(xi);
		candidateList.add(jiang);
		
		new ServerFrame(candidateList);
	}

}
