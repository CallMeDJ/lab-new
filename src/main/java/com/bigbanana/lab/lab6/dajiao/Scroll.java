package com.bigbanana.lab.lab6.dajiao;

import com.bigbanana.lab.lab6.ScrollMe;
import com.bigbanana.lab.utils.Printer;
import org.junit.Test;

/**
 * Created by callmedj on 17/11/14.
 */
public class Scroll extends ScrollMe{


    @Test
    public void testing(){
        Scroll instance = new Scroll();
        super.testing(instance);
    }

    @Override
    public void scroll(int num){
        int size = num;
        int initX,initY;
        int currentNum = (int)Math.pow(size,2) ;

        int maxLength = String.valueOf(currentNum).length();
        Integer[][] target = new Integer[size][size];


        Position current = new Position();

        if(size == 1){
            target[0][0] = 1;
            Printer.printArray(target,maxLength);
            return;
        }
        else if(size % 2 ==0){
            initX = size-1;
            initY = 0;
            current.setX(initX).setY(initY);
            current.setDirection(Direction.RIGHT);

        }else{
            initX = 0;
            initY = size-1;
            current.setX(initX).setY(initY);
            current.setDirection(Direction.LEFT);
        }
          target[current.getX()][current.getY()] = currentNum--;

        for(int i = 1 ; i < Math.pow(size,2);i++){
            current =   move(target,current,currentNum--);
           // Printer.printArray(target);
        }

        Printer.printArray(target,maxLength);
    }



    private Position move(Integer[][] target , Position currentPosition,int num){
          int row = target.length;
          int column = target[0].length;

        int nextX = 0,nextY = 0;

         Direction direction = currentPosition.getDirection();
         Position next = new Position();
         switch (direction){
            case LEFT:
                if(currentPosition.getY()-1 >= 0 && target[currentPosition.getX()][currentPosition.getY()-1] == null){
                    nextX = currentPosition.getX();
                    nextY = currentPosition.getY()-1;
                    next.setDirection(Direction.LEFT);
                }else{
                    nextX = currentPosition.getX()+1;
                    nextY = currentPosition.getY();
                    next.setDirection(Direction.DOWN);
                }


                break;
            case RIGHT:

                if(currentPosition.getY()+1 <= column - 1 && target[currentPosition.getX()][currentPosition.getY()+1] == null){
                    nextX = currentPosition.getX();
                    nextY = currentPosition.getY()+1;
                    next.setDirection(Direction.RIGHT);
                }else{
                    nextX = currentPosition.getX()-1;
                    nextY = currentPosition.getY();
                    next.setDirection(Direction.UP);
                }

                break;

            case UP:

                if(currentPosition.getX()-1 >= 0  && target[currentPosition.getX()-1][currentPosition.getY()] == null){
                    nextX = currentPosition.getX()-1;
                    nextY = currentPosition.getY();
                    next.setDirection(Direction.UP);
                }else{
                    nextX = currentPosition.getX();
                    nextY = currentPosition.getY()-1;
                    next.setDirection(Direction.LEFT);
                }



                break;

            case DOWN:

                if(currentPosition.getX()+1 <= row-1  && target[currentPosition.getX()+1][currentPosition.getY()] == null){
                    nextX = currentPosition.getX()+1;
                    nextY = currentPosition.getY();
                    next.setDirection(Direction.DOWN);
                }else{
                    nextX = currentPosition.getX();
                    nextY = currentPosition.getY()+1;
                    next.setDirection(Direction.RIGHT);
                }

                break;
        }

        next.setX(nextX).setY(nextY);
        target[nextX][nextY] = num;

        return next;
    }

    private enum   Direction{
        LEFT,RIGHT,UP,DOWN
    }





    private class Position{
        private int x;
        private int y;
        private Direction direction;

        Position(){}
        Position(int x,int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public Position setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public Position setY(int y) {
            this.y = y;
            return this;
        }

        public Direction getDirection() {
            return direction;
        }

        public Position setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }
    }
}
