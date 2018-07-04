import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Maze {
    static int size_canvas = 511;//используется для рассчета размера ячеек
    static int thikness = 1;
    int delay=100;
    int width,height;
    int[][] maze_wall;//массив который хранит наше поле
    boolean dont_edit=false;//разрешение рисовки поля(при рандоме нельзя)
    PaintCanvas canvas;//для рисовки нашего поля
    Deque<Integer[]> stack = new ArrayDeque<>();//очередь для прохода по полю


    Maze(Canvas canv,int height , int width){ //конструктор: разрисовку поля, ширину, высоту
        canvas = new PaintCanvas(canv,height,width);
        this.height = height;
        this.width = width;
        maze_wall = new int[width][height];
        paint();
    }
    void on_mouse_dragged(int x,int y){ //метод обработки движения мыши на Canvas.
        //Int x, int y — координаты мыши
        int coord_x = (x*width)/size_canvas;//координата в массиве
        int coord_y = (y*height)/size_canvas;//координата в массиве
        if ((coord_x==0 && coord_y==0) || (coord_x==width-1 && coord_y==height-1) || (dont_edit)) return;
        canvas.paint_selection(coord_x, coord_y,maze_wall[coord_x][coord_y]!=0);//раскрашиваем выбранную ячейку

    }

    void set_delay(int delay){ //— метод установки задержки
        this.delay = delay; //     Int delay — время задержки
    }

    void paint(){ //разрисовываем поле
        if (dont_edit) return;
        canvas.paint_canvas(); //прорисовываем начальное поле(белое с входом и выходом)
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if (maze_wall[i][j]!=0) canvas.draw_rect(i, j, Color.BLACK,Color.gray);//если в ячейке не ноль,
                // то там стена и красим ее в серый
            }
        }

    }



    void bad_rand_generarion(){ //метод случайной генерации лабиринта
        maze_wall = new int[width][height];//создается поле
        for(int i=0;i<width*height*0.5;i++){
            int x = (int) (Math.random()*width);
            int y = (int) (Math.random()*height);
            maze_wall[x][y]=1;//прописывается стена
        }
        maze_wall[0][0]=0;//конечная точка помечается 0 чтобы не создалась стена
        maze_wall[width-1][height-1]=0;//начальная точка помечается 0 чтобы не создалась стена
        paint();//рисуем
    }

    void on_mouse_clicked(int x,int y){//метод обработки нажатия мыши на Canvas.
        int coord_x = (x*width)/size_canvas;
        int coord_y = (y*height)/size_canvas;
        if ((coord_x==0 && coord_y==0) || (coord_x==width-1 && coord_y==height-1) || (dont_edit)) return;
        if (maze_wall[coord_x][coord_y]!=0)  maze_wall[coord_x][coord_y] = 0;
        else maze_wall[coord_x][coord_y] = 1;//чтобы менять наличие и отсутствие стеныв массиве(с 0 на 1 либо с 1 на 0)
        canvas.paint_selection(coord_x, coord_y,maze_wall[coord_x][coord_y]!=0);//прорисовываем стену

    }

    void set_height(int height){ // метод установки высоты
        this.height = height;//заносит новую высоту в наш класс
        maze_wall = new int[width][height];//создает новый массив
        canvas.set_height(height);//передаем новую высоту для отрисовки поля
    }

    void clear(){ //очищение поля полностью
        stack.clear();
        dont_edit=false;//разрешаем рисовать стены
        maze_wall = new int[width][height];//0 забиваеим массив==очищаем
        canvas.paint_canvas();//прорисует начальное поле
    }

    void go_maze(){ //метод построения маршрута в лабиринте
        try {
            dont_edit=true;//непосещенные вершины
            maze_wall[width-1][height-1]=2;//начальная точка помечается 2
            Find_way_out(width-1, height-1);//ищем путь из начальной точки
            if(maze_wall[0][0]!=2) {
                JFrameOK dialog = new JFrameOK();
                dialog.setVisible(true);
                System.exit(0);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean check_left(int x , int y){ //метод проверки возможности движения влево
        if (x<1) return false; //если ограничения поля
        if (maze_wall[x-1][y]==0) return true;//может
        else return false;//иначе стена
    }


    boolean check_right(int x , int y){
        if (x >width-2) return false;
        if (maze_wall[x+1][y]==0) return true;
        else return false;
    }


    boolean check_up(int x , int y){
        if (y<1) return false;
        if (maze_wall[x][y-1]==0) return true;
        else return false;
    }

    boolean check_down(int x , int y){
        if (y>height-2) return false;
        if (maze_wall[x][y+1]==0) return true;
        else return false;
    }


    void Find_way_out(int x, int y) throws InterruptedException{ //метод поиска пути
        if(x==0 && y==0) return; //если дошли до конца(выхода)
        Thread.sleep(delay);//задержка
        Integer[] arr;
        arr = new Integer[2];
        arr[0]=x;
        arr[1]=y;
        stack.push(arr);//добавили координаты в очередь

        if (check_up(x,y)){ //если можем идти вверх
            maze_wall[x][y-1]=2;//помечаем верхнюю ячейку как посещенную=2
            canvas.draw_rect(x,y-1,Color.BLACK,Color.GREEN);//раскрашиваем ее в зеленый
            Find_way_out(x,y-1);//рекурсивно запускаем для этой ячейки метод поиска пути
            return;
        }

        if (check_left(x,y)){
            maze_wall[x-1][y]=2;
            canvas.draw_rect(x-1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x-1,y);
            return;
        }

        if (check_down(x,y)){
            maze_wall[x][y+1]=2;
            canvas.draw_rect(x,y+1,Color.BLACK,Color.GREEN);
            Find_way_out(x,y+1);
            return;
        }

        if (check_right(x,y)){
            maze_wall[x+1][y]=2;
            canvas.draw_rect(x+1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x+1,y);
            return;
        }

        canvas.draw_rect(x,y,Color.BLACK,Color.RED);//если идти некуда, раскрашиваем екрасным где стоим
        if (stack.size()>1){ //если очередь не пуста
            arr = stack.pop();//выталкиваем координаты следующей ячейки
            arr = stack.pop();
            Find_way_out(arr[0],arr[1]);//запускаем метод поиска пути
        }
    }



    void set_width(int width){ // метод установки ширины
        this.width = width;
        maze_wall = new int[width][height];
        canvas.set_width(width);
    }

}








