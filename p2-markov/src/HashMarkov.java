import java.util.*;

/**
 * Implements the MarkovInterface to generate random text based
 * on a training text. Searches over training text to generate
 * each random word of generated text.
 * For use in Compsci 201, Fall 2022, Duke University
 * @author Adeildo Vieira Silva Neto (av259)
 * @author Brandon Fain
 */
// no outofbound exception now (OK) (Tested2) (3 OK)

public class HashMarkov extends BaseMarkov{
    private HashMap<WordGram, ArrayList<String>> map;

	public HashMarkov() {
		this(2);		
	}

	public HashMarkov(int order){
		super(order);
		map = new HashMap<WordGram, ArrayList<String>>();
	}

	@Override
	public void setTraining(String text){
		super.myWords = text.split("\\s+");
		map.clear();
		WordGram tempWG;
		for (int i = 0; i < myWords.length-super.myOrder; i++) {
			tempWG = new WordGram(myWords, i, super.myOrder);
			map.putIfAbsent(tempWG, new ArrayList<String>());
			if (myWords.length==myOrder+i) {
				map.get(tempWG).add("");
			}
			else{
			map.get(tempWG).addAll(Arrays.asList(Arrays.copyOfRange(myWords, i+super.myOrder, i+super.myOrder+1)));
			}
		}
	}

    @Override
	// Time Complexity: O(1)
	public List<String> getFollows(WordGram wgram) {
		List<String> followWords = map.get(wgram);
        if(followWords!=null){
            return followWords;
        }
        return new ArrayList<String>();
	}
}