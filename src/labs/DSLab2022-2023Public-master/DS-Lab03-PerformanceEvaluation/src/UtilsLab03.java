import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class UtilsLab03 {

	private static final String matrixOutFile = "TimerPrintMatrix.txt";

	public static void printMatrixToFile(Matrix m, int size) {
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(matrixOutFile), "utf-8");
			for (int i = 1; i <= size; i++) {
				for (int j = 1; j <= size; j++) {
					if (j != 1) {
						writer.write('\t');
					}
					writer.write(String.valueOf(m.get(i, j)));
				}
				writer.write('\n');
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
	}

	public static class TimerFactory {
		public static Timer getTimer() {
			return new Timer();
		}
	}

}
