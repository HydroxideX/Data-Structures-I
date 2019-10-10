package eg.edu.alexu.csd.datastructure.iceHockey;
import javax.print.DocFlavor;
import java.awt.Point;
import java.util.*;
class IplayersFinder implements IPlayersFinderInterface {
    public java.awt.Point[] findPlayers(String[] photo,int team,int threshold){
        Point[] x = new Point[20000];
        Point V = new Point();
        int[] Area = new int[5];
        Area[0] = 0;
        if(photo.length==0 || photo[0] == null) {
            Point[]z = new Point[0];
            return z;
        }
        boolean[][] visited = new boolean[photo.length][photo[0].length()];
        int ct = 0;
        for(int i=0;i<photo.length;i++) {
            for(int j = 0;j<photo[i].length();j++) {
                if(photo[i].charAt(j) == team+'0' && visited[i][j] !=true ) {
                    Area[1] = i; Area[2] = j; Area[3] = i; Area[4] = j;
                    V = dfs(photo, team, threshold, i, j, visited,Area);
                    int temp = V.x;
                    V.x = V.y; V.y = temp;
                    if(Area[0] >= threshold)
                        x[ct++] = V;
                    Area[0] = 0;
                }
            }
        }

        int size = 0;
        for(int i = 0; x[i]!=null;i++){
            size++;
        }
        Point[] end = new Point[size];
        for(int i = 0;i<size;i++){
            end[i] = x[i];
        }
        for(int i = 0;i<end.length;i++){
            for( int j = 0;j<end.length-1;j++){
                if(end[j].x>end[j+1].x){
		    swap(end[j],end[j+1]);
                }
                else if(end[j].x==end[j+1].x && end[j].y>end[j+1].y){
		    swap(end[j],end[j+1]);
		}
            }
        }
        return end;
    }
    public static void swap(Point x, Point y){
        Point z = new Point();
        z.x = x.x; z.y = x.y;
        x.x = y.x; x.y = y.y;
        y.x = z.x; y.y = z.y;
    }

    java.awt.Point dfs(String[] photo,int team, int threshold,int i,int j,boolean[][] visited,int[] Area){
        Point z = new Point(Area[1]+Area[3]+1,Area[2]+Area[4]+1);
        if(i == photo.length || i < 0 || j==photo[i].length() || j < 0 || visited[i][j] || photo[i].charAt(j)!=team+'0')
	    return z;
        visited[i][j] = true;
    	Area[0] += 4;
        if(i<Area[1])
		Area[1] = i;
        if(i>Area[3])
		Area[3] =i;
        if(j<Area[2])
		Area[2] = j;
    	if(j>Area[4])
		Area[4] = j;
        z = dfs(photo,team,threshold,i-1,j,visited,Area);
        z = dfs(photo,team,threshold,i+1,j,visited,Area);
        z = dfs(photo,team,threshold,i,j+1,visited,Area);
    	z = dfs(photo,team,threshold,i,j-1,visited,Area);
    	return z;
    }
}
