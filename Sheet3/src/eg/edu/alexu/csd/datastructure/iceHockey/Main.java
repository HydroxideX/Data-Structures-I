package eg.edu.alexu.csd.datastructure.iceHockey;
import javax.print.DocFlavor;
import java.awt.Point;
import java.util.*;
class Main implements IPlayersFinder {
    public java.awt.Point[] findPlayers(String[] photo,int team,int threshold){
        Point[] x = new Point[20000];
        Point V = new Point();
        int[] Area=new int[5];
        Area[0] = 0;
        if(photo.length==0) {
            Point[]z = new Point[0];
            return z;
        }
        boolean[][] visited = new boolean[photo.length][photo[0].length()];
        int ct = 0;
        for(int i=0;i<photo.length;i++)
        {
            for(int j = 0;j<photo[i].length();j++)
            {
                if(photo[i].charAt(j) == team+'0' && visited[i][j] !=true ) {
                    Area[1] = i;
                    Area[2] = j;
                    Area[3] = i;
                    Area[4] = j;
                    V = bfs(photo, team, threshold, i, j, visited,Area);
                    int temp = V.x;
                    V.x = V.y;
                    V.y = temp;
                    if(Area[0] >= threshold)
                        x[ct++] = V;
                    Area[0] = 0;
                }
            }
        }

        int j = 0;
        for(int i = 0; x[i]!=null;i++){
            j++;
        }
        Point[] end = new Point[j];
        for(int i = 0;i<j;i++){
            end[i] = x[i];
        }
        for(int i = 0;i<end.length;i++){
            for( j = 0;j<end.length-1;j++){
                if(end[j].x>end[j+1].x){
                    Point Temp = end[j];
                    end[j] = end[j+1];
                    end[j+1] = Temp;
                }
                else if(end[j].x==end[j+1].x && end[j].y>end[j+1].y){
                    Point Temp = end[j];
                    end[j] = end[j+1];
                    end[j+1] = Temp;
                }
            }
        }
        return end;
    }
    public java.awt.Point bfs(String[] photo,int team, int threshold,int i,int j,boolean[][] visited,int[] Area){
        Point z = new Point(Area[1]+Area[3]+1,Area[2]+Area[4]+1);
        if(i == photo.length)
            return z;
        if(i<0) return z;
        if(j==photo[i].length())
            return z;
        if(j<0) return z;

        if (visited[i][j] ) return z;
        if(photo[i].charAt(j)!=team+'0') return z;
        else{
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
            z = bfs(photo,team,threshold,i-1,j,visited,Area);
            z = bfs(photo,team,threshold,i+1,j,visited,Area);
            z = bfs(photo,team,threshold,i,j+1,visited,Area);
            z = bfs(photo,team,threshold,i,j-1,visited,Area);
            return z;
        }
    }
}
