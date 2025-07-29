public class Cube {
    private double[] p1;
    private double[] p2;
    private double[] p3;
    private double[] p4;
    private double[] p5;
    private double[] p6;
    private double[] p7;
    private double[] p8;
    public Cube (){
        int scale = 200;
        int dist = 5;
        p1 = new double[]{1 * scale, 1 * scale, -1 + dist};
        p2 = new double[]{1 * scale, -1 * scale, -1 + dist};
        p3 = new double[]{-1 * scale, 1 * scale, -1 + dist};
        p4 = new double[]{-1 * scale, -1 * scale, -1 + dist};
        p5 = new double[]{1 * scale, 1 * scale, 1 + dist};
        p6 = new double[]{1 * scale, -1 * scale, 1 + dist};
        p7 = new double[]{-1 * scale, 1 * scale, 1 + dist};
        p8 = new double[]{-1 * scale, -1 * scale, 1 + dist};
    }
    
}
