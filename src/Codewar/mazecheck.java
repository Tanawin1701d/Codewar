package Codewar;

import java.util.*;
import java.util.stream.*;
import java.awt.Point;
import java.util.function.Function;


public class mazecheck {

    final static private Point[]         MOVES = {new Point(0,1), new Point(1,0), new Point(0,-1), new Point(-1,0)};
    final static private List<Character> DIRS  = Arrays.asList('>', 'v', '<', '^');


    public static List<Character> escape(char[][] maze) {

        int   lX    = maze.length,
                lY    = maze[0].length;
        Point start = findMe(maze, lX, lY),
                dir   = MOVES[ DIRS.indexOf(maze[start.x][start.y]) ],
                next  = null,
                pos   = null;

        Function<Point,Boolean>
                isAtExit          = p -> p.x==0 || p.x==lX-1 || p.y==0 || p.y==lY-1,
                isInMazeAndOpened = p -> 0<=p.x && p.x<lX && 0<=p.y && p.y<lY && maze[p.x][p.y]==' ';

        if (isAtExit.apply(start)) return Arrays.asList();

        Map<Point,Point[]>  seens = new HashMap<>();
        ArrayDeque<Point[]> q     = new ArrayDeque<>();
        q.push(new Point[] {start, dir});

        while (!q.isEmpty()) {
            Point[] data = q.poll();
            pos = data[0];
            dir = data[1];

            for (Point m: MOVES) {
                next = new Point(pos.x+m.x, pos.y+m.y);

                if (isInMazeAndOpened.apply(next) && !seens.containsKey(next)) {
                    q.push(new Point[] {next, m});
                    seens.put(next, new Point[] {pos, dir, m});
                    if (isAtExit.apply(next)) return buildPath(seens, next, start);
                }
            }
        }
        return Arrays.asList();
    }

    private static Point findMe(char[][] maze, int lX, int lY) {
        return IntStream.range(0,lX).boxed()
                .flatMap( x -> IntStream.range(0,lY)
                        .filter(y -> maze[x][y]!=' ' && maze[x][y]!='#' )
                        .mapToObj( y -> new Point(x,y) ))
                .findAny().get();
    }

    private static List<Character> buildPath(Map<Point,Point[]> seens, Point pos, Point start) {

        StringBuilder sb = new StringBuilder();
        while (!start.equals(pos)) {
            Point[] data = seens.get(pos);
            pos = data[0];
            Point dir = data[1], nextDir = data[2];

            int dot   = dir.x * nextDir.x + dir.y * nextDir.y,
                    cross = dir.x * nextDir.y - dir.y * nextDir.x;

            if (dot!=0) sb.append(dot<0   ? "FB":"F");
            else        sb.append(cross>0 ? "FL":"FR");
        }
        return sb.reverse().toString().chars().mapToObj(c -> new Character((char) c)).collect(Collectors.toList());
    }
}
