/*
 * Class PathFinder compute a path from the entrance of the pyramid to all the treasure chambers
 * @Rubaisha  CS1027 Assignment 3
 */


import java.io.FileNotFoundException;
import java.io.IOException;

public class PathFinder {
	
	private Map pyramidMap; // 

	public PathFinder(String fileName) throws InvalidMapCharacterException, FileNotFoundException, IOException {
		pyramidMap = new Map(fileName);
	}
	
	/*
	 *  saved a local variable to keep track of the amount of treasure found so far 
	 *  compare it to total number of treasures found in the whole map 
	 *  break the loop if they are equal
	 */
	public DLStack<Chamber> path() { // suppose to push neighbor chamber path every time a loop(check if path is empty)
		int i = 0;
		DLStack<Chamber> findPath = new DLStack<Chamber>();
		Chamber chamberCheck = pyramidMap.getEntrance();
		int teasureNo = pyramidMap.getNumTreasures();
		
		findPath.push(chamberCheck);
		chamberCheck.markPushed();
		
		// while loop that loops through to find the path 
		while (this.pyramidMap != null) {
			Chamber thePath = findPath.peek();
			if (thePath.isTreasure()) {
				i++;
				if (i == teasureNo) { //  check for total number of treasure and number of treasure found and than break
					break;
				}}
			
			Chamber best = bestChamber(thePath);
			if (best != null) {
				findPath.push(best);
				best.markPushed();
				continue;
			} else {
			Chamber popTop = findPath.pop();
			popTop.markPopped();
		}}
		
		return findPath;
	}		
	        
	public Map getMap() {
        return pyramidMap;
    }

	 /*
	  * checks if the chamber is dim
	  * if its null, sealed or lighted return false 
	  * if it is not null. not sealed, not light and the neighbor is not light
	  */

	 public boolean isDim(Chamber currentChamber) {
		boolean dim = false;
		if (currentChamber == null){
	            return dim;}
		else if (currentChamber.isSealed()) {
			return dim;
		}
		else if (currentChamber.isLighted()) {
			return dim;
		}
		// check if its not sealed and not lighted, than loop through the neighbor to see if they are not null or is lighted
		else if ((!currentChamber.isSealed()) && (!currentChamber.isLighted())) {
				for (int i = 0; i < 6; i++ ) {
					if (currentChamber.getNeighbour(i) != null) {
					if (currentChamber.getNeighbour(i).isLighted()) {
						dim = true;
				}}}
					return dim;
					}
		return dim;
		}
	 
	 
	 
	 /*
	  * looped through the surrounding neighbor 
	  * returns the best one treasure -> lighted -> dim
	  * return null if none are true
	  * 
	  */
       public Chamber bestChamber(Chamber currentChamber) {
	       Chamber bestCham = null;
	       int checkTreasure = 0;
	       int checkLight = 0;
	       int checkDim = 0;
	       
	       // while loop that loops through the neighbors and see if it meets requirements if treasure return it as best
	       while (checkTreasure < 6) {
	    	   Chamber nextTreasure = currentChamber.getNeighbour(checkTreasure);
	    	   if (nextTreasure != null) {
	    	    if (nextTreasure.isTreasure() && !(nextTreasure.isMarked())){
	    	    	return bestCham = nextTreasure;
	    	    
	    	    }}
	    	    checkTreasure++;}
	       
 	       // while loop that loops through the neighbors and see if it meets requirements if lighted return it as best
    	    while (checkLight < 6) {
 	    	   Chamber nextLight = currentChamber.getNeighbour(checkLight);
 	    	   
    	    	if (nextLight != null) {
	    	    if (nextLight.isLighted() && !(nextLight.isMarked())) {
	    	    	return bestCham  =nextLight;
	    	    }
	    	    }
	    	    checkLight++;
	    	    }
    	    
 	       // while loop that loops through the neighbors and see if it meets requirements if dim return it as best
    	    while (checkDim < 6) {
  	    	   Chamber nextDim = currentChamber.getNeighbour(checkDim);

    	    	if (nextDim != null) {
	    	    	if (isDim(nextDim) && !(nextDim.isMarked())) {
	    	    
	    	    	return bestCham = nextDim;
	    	    	}
	    	    	}
	    	    	checkDim++;
	    	    }
	    
	    	    
    	    
	        return bestCham;
	    }
       

}