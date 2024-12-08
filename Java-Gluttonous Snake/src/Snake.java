/*
 * Created on 2024-04-08
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.ArrayList;

public class Snake {
    private SnakeSegment head;
    private boolean grow = false;
    private Direction direction = Direction.RIGHT; 
    private final static int BOARD_WIDTH = 800;
    private final static int BOARD_HEIGHT = 600;


    public Snake() {
        // You may change this code for extra credit (implement some fancy stuff!)
        // Feel free to make the starting position random
        Position startingPosition = new Position(10, 10);
        head = new SnakeSegment(startingPosition);
    }

    // TODO: The snake should grow whenever it "eats" a food item
    public void grow() {
        grow = true;
    }

    // TODO: Remove the last node (tail) of the snake, leave head untouched
    private void removeTail() {
        if (head == null || head.getNext() == null) {
            return; 
        }

        SnakeSegment current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }

        // Now 'current' is the second-to-last segment, so remove the last one
        current.setNext(null);
    }

    // Returns true if the snake is colliding with itself
    public boolean isColliding() {
        if (isInSnake(head.getPosition())) {
            return true;
        }
        return false;
    }

    // TODO: Implement isInSnake
    // Returns false if the specified position is inside the body of the snake
    public boolean isInSnake(Position position) {
        SnakeSegment current = head.getNext();
        while (current != null) {
            if(!position .equals(current.getPosition())) {
            current = current.getNext();} else{return true;}
        }
        return false;
        
    }

    // TODO: Implement setDirection
    // Sets the direction the snake will move in
    public void setDirection(Direction direction) {
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
        this.direction == Direction.DOWN && direction == Direction.UP ||
        this.direction == Direction.LEFT && direction == Direction.RIGHT ||
        this.direction == Direction.RIGHT && direction == Direction.LEFT) {
        // Don't allow the snake to reverse
        return;
    }

    this.direction = direction;
    }

    // TODO: Get the length of the snake 
    public int getLength() {
        int length = 0;
        SnakeSegment current = head;
    
        while (current != null) {
            length++;
            current = current.getNext();
        }
    
        return length;
    }
    

    // Moves the snake by one in the next direction
    // TODO: Implement move
        // HINT: You may add and remove nodes here
        public void move() {
            Position newHeadPosition = head.getPosition().add(direction.deltaPosition());
            SnakeSegment newHead = new SnakeSegment(newHeadPosition);
            newHead.setNext(head);
            head = newHead;
    
            if (!grow) {
                SnakeSegment current = head;
                while (current.getNext().getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(null); 
            } else {
                grow = false;
            }
             // ...

        // TODO: Uncomment!
        // if (!grow) {
        //    removeTail();
        // } else {
        //    grow = false;
        // }
        }
    

       


    // TODO: Return the head of the snake
    public SnakeSegment getHead() {

        return head;
    }

    // TODO: Return the start of the body (NOT the head!)
    public SnakeSegment getBody() {
       return (head != null) ? head.getNext() : null;
}

    public void pause() {
        // OPTIONAL: Pause/unpause the snake
    }

    public Direction getMove(ArrayList<Food> food) {
        // OPTIONAL: Implement an algorithm that moves the food for us!
        return null;
    }

    public boolean isPositionOutOfBounds() {
        Position position = head.getPosition();
        if (position.x < 0 || position.x > 800 || position.y < 0 || position.y > 600) {
            System.out.println("Out of bounds");
            return true;
        } 
        return false;
    }
}

class SnakeSegment {
    private Position position;
    private SnakeSegment next;

    public SnakeSegment(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public SnakeSegment getNext() {
        return this.next;
    }

    public void setNext(SnakeSegment next) {
        this.next = next;
    }
}
