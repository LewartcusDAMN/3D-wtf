
public class Utils{
    public static int near_plane;
    public Utils(){

        near_plane = 1;
    }

    public static double near_plane_intersection(double xy, double z, int FOV){
        double FOV_radians = Math.toRadians(FOV);
        return xy / (z * Math.tan(FOV_radians/2));
        //return (xy * near_plane)/ (z * Math.tan(FOV_radians/2));
    }

}