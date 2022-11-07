package labs.lab02;

public class Main {

    public static void main(String[] args) {
        DiagonalMatrix mat = new DiagonalMatrix(4);
        mat.set(1,1,2);
        mat.set(1,2,1);
        mat.set(1,3,3);
        mat.set(1,4,4);
        mat.set(4,1,7);
        mat.set(4,2,6);
        mat.set(4,3,5);
        mat.set(4,4,2);
        System.out.println(mat);

    }
}
