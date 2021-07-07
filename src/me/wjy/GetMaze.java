package me.wjy;

import java.io.*;

/**
 * 获取 txt 中的迷宫
 *
 * @author 王金义
 */
public class GetMaze {
    public static char[][] get(String path) throws Exception {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        InputStreamReader inputStreamReader = fileReader;

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        int x = 0;
        int y = 0;
        String tempString = bufferedReader.readLine();
        y = tempString.length();
        x++;
        while ((tempString = bufferedReader.readLine()) != null) {
            x++;
        }
        File file1 = new File(path);
        fileReader = new FileReader(file1);
        // XXX 代码多余
        inputStreamReader = fileReader;
        bufferedReader = new BufferedReader(inputStreamReader);

        char[][] maze = new char[x][y];
        int temp = 0;
        x = 0;
        y = 0;
        while ((temp = bufferedReader.read()) != -1) {
            if (temp == 13) {
                continue;
            }
            if (temp == 10) {
                x++;
                y = 0;
                continue;
            }
            maze[x][y] = (char) temp;
            y++;
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.print("\n");
        }
        inputStreamReader.close();
        bufferedReader.close();
        fileReader.close();
        return maze;
    }
}
