import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import static java.lang.Integer.max;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Maze {
    static int size_canvas = 511;//используется для рассчета размера ячеек
    static int thikness = 1;//толщина черной линии в лабиринте
    int delay = 100;
    int width, height;
    int[][] maze_wall;//массив который хранит наше поле
    boolean dont_edit = false;//разрешение рисовки поля(при рандоме нельзя)
    PaintCanvas canvas;//для рисовки нашего лабиринта
    Deque<Integer[]> stack = new ArrayDeque<>();//очередь для прохода по полю


    Maze(Canvas canv, int height, int width) { //конструктор: разрисовку поля, ширину, высоту
        canvas = new PaintCanvas(canv, height, width);
        this.height = height;
        this.width = width;
        maze_wall = new int[width][height];
        paint();
    }

    void on_mouse_dragged(int x, int y) { //метод обработки движения мыши на Canvas.
        /*//Int x, int y — координаты мыши
        int coord_x = (x * width) / size_canvas;//координата в массиве
        int coord_y = (y * height) / size_canvas;//координата в массиве
        if ((coord_x == 0 && coord_y == 0) || (coord_x == width - 1 && coord_y == height - 1) || (dont_edit)) return;
        canvas.paint_selection(coord_x, coord_y, maze_wall[coord_x][coord_y] != 0);//раскрашиваем выбранную ячейку
*/  }

    void set_delay(int delay) { //— метод установки задержки
        //this.delay = delay; //     Int delay — время задержки
    }

    void paint() { //разрисовываем поле
        //if (dont_edit) return;
        canvas.paint_canvas(); //прорисовываем начальное поле(белое с входом и выходом)
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (maze_wall[i][j] != 0) canvas.draw_rect(i, j, Color.BLACK, Color.gray);//если в ячейке не ноль,
                // то там стена и красим ее в серый
            }
        }

    }


    void bad_rand_generarion() { //метод случайной генерации лабиринта
       /* maze_wall = new int[width][height];//создается поле
        for (int i = 0; i < width * height * 0.5; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            maze_wall[x][y] = 1;//прописывается стена
        }
        maze_wall[0][0] = 0;//конечная точка помечается 0 чтобы не создалась стена
        maze_wall[width - 1][height - 1] = 0;//начальная точка помечается 0 чтобы не создалась стена
        paint();//рисуем
        */
    }

    void on_mouse_clicked(int x, int y) {//метод обработки нажатия мыши на Canvas.

    }

    void set_height(int height) { // метод установки высоты
           }

    void clear() { //очищение поля полностью
    }

    void go_maze() { //метод построения маршрута в лабиринте
    }

    void Find_way_out(int x, int y) throws InterruptedException{ //метод поиска пути

        }

    }



   // void set_width(int width){//задает ширину

   // }


