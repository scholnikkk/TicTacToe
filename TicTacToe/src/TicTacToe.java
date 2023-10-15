import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //Empty field
    public static final int FIELD_X = 10; //Field with X
    public static final int FIELD_O = 200; //Field with O
    int[][] field;
    boolean isXturn;
    public TicTacToe(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Включаем получение событий от мыши
        field = new int[3][3];
        initGame();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0,0,getWidth(),getHeight());
        drawGrid(graphics);
        drawXO(graphics);
    }

    protected void processMouseEvent(MouseEvent mouseEvent){
        super.processMouseEvent(mouseEvent);
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){ //check left button
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            //translate coordinates to indexes in field array
            int i = (int) ((float) x / getWidth()*3);
            int j = (int) ((float) y / getHeight()*3);
            //check that chosen ячейка empty //and we can go to this ячейка
            if (field[i][j] == FIELD_EMPTY){
                //Проверяем чей ход
                field[i][j] = isXturn?FIELD_X:FIELD_O;
                isXturn = !isXturn; // change flag of ход (меняем флаг хода) меняет значение на противоположное
                repaint();//перерисовка компонента
                int res = checkState();
                if(res!=0){
                    if(res == FIELD_O *3){
                        //Win
                        JOptionPane.showMessageDialog(this,"O win!","Win!",JOptionPane.INFORMATION_MESSAGE);
                    } else if (res == FIELD_X * 3){
                        JOptionPane.showMessageDialog(this,"X win!","Win!",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,"Ничья!","Ничьяяяя",JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame();//reload game
                    repaint();//repaint window
                }
            }
        }
    }



    void drawGrid(Graphics graphics){
        int width = getWidth();
        int height = getHeight();
        int dw = width / 3;
        int dh = height / 3;
        graphics.setColor(Color.BLUE);
        for(int i = 1; i<3; i++){
            graphics.drawLine(0, dh*i, width, dh*i);
            graphics.drawLine(dw*i,0,dw*i,height);
        }
    }

    public void initGame(){
        for (int i = 0; i<3; ++i){
            for (int j = 0; j<3; ++j){
                field[i][j] = FIELD_EMPTY; // With this part of code we clean our game window;
            }
        }
        isXturn = true;
    }
    void drawX(int i, int j, Graphics graphics){
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x, y, x+dw, y+dh); // line from upper-left corner to lower-right
        graphics.drawLine(x, y+dh, x+dw, y); // line from lower-left corner to upper-right
    }

    void drawO(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x+5*dw/100, y, dw*9/10, dh);
    }

    void drawXO(Graphics graphics){
        for(int i = 0 ; i<3; ++i){
            for (int j = 0; j < 3; ++j){
                if(field[i][j] == FIELD_X){
                    drawX(i,j,graphics);
                } else if (field[i][j] == FIELD_O){
                    drawO(i,j,graphics);
                }
            }
        }
    }

    int checkState(){
        int diag = 0;
        int diag2 = 0;
        for(int i = 0; i<3; i++){
            diag += field[i][i];//сумма значений по диагонали от левого угла
            diag2 += field[i][2-i];//сумма значений по диагонали от правого угла
        }
        //если по диагонали стоят одни крестики или одни нолики выходим из метода
        if(diag == FIELD_O * 3 || diag == FIELD_X * 3){return diag;}
        if(diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3){return diag2;}
        int check_i;
        int check_j;
        boolean hasEmpty = false;
        //будем бегать по всем рядам
        for(int i = 0; i<3; i++){
            check_i = 0;
            check_j = 0;
            for(int j = 0; j<3; j++){
                //суммируем знаки в текущем ряду
                if(field[i][j] == 0){
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[i][j];
            }
            //если выигрыш крестика или нолика, то выходим
            if (check_i == FIELD_O * 3 || check_i == FIELD_X * 3){return check_i;}
            if (check_j == FIELD_O * 3 || check_j == FIELD_X * 3){return check_j;}
        }
        if(hasEmpty) return 0; else return -1;
    }
}
