import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		
		//TODO add your code here.

	}

}
