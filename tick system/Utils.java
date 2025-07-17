
public class Utils{
    public Utils(){

    }

    public static double near_plane_intersection(double xy, double z, int FOV){
        double FOV_radians = Math.toRadians(FOV);
        return xy / (z * Math.tan(FOV_radians/2));
    }

}