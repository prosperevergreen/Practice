import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;




public final class PaintCanvas extends Canvas {
    static int size_canvas = 510;
    static int thikness = 1;
    Graphics2D graphic;
    Canvas canvas;
    int width,height;//ширина, высота
    int coord_x=1,coord_y=1;
    boolean clicked = false;

    PaintCanvas(Canvas canvas,int width,int height){
        this.graphic =(Graphics2D) canvas.getGraphics();
        this.canvas = canvas;
        this.height=height;
        this.width = width;
        paint_canvas();
    }

    void set_height(int height){//метод установки высоты

    }

    void set_width(int width){//метод установки ширины

    }

    void paint_canvas(){//метод отрисовки поля лабиринта
        graphic.setPaint(Color.WHITE);
        graphic.fillRect(0,0,510,510);//рисует заполненный прямоугольник(сетка)
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                draw_rect(i,j,Color.BLACK,Color.white);
            }
        }
        draw_rect(0,0,Color.BLACK,Color.pink);//ячейка конца(выход)
        draw_rect(width-1,height-1,Color.BLACK,Color.cyan);//ячейка начала(вход)
    }

    void draw_rect(int x,int y,Color color,Color c_filling){//метод рисования ячейки
        graphic.setPaint(color);//черный
        graphic.fillRect((size_canvas/width)*x,(size_canvas/height)*y,size_canvas/width,size_canvas/height); //рисуется 1 квадрат
        graphic.setPaint(c_filling);//второй цвет/(серый/зеленый/красный/белый)
        graphic.fillRect((size_canvas/width)*x+thikness,(size_canvas/height)*y+thikness,(size_canvas/width)-(thikness*2),(size_canvas/height)-(thikness*2));//рисуется более маленикий квадрат
    }


}

