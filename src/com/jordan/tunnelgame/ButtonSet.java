package com.jordan.tunnelgame;

import com.jordan.framework.Graphics;
import com.jordan.framework.Input;

import java.util.ArrayList;
import java.util.List;

public class ButtonSet {

    public ArrayList<Button> buttons;
    public int[][] buttonCoords;

    public ButtonSet(ArrayList<Button> buttons, int[][] buttonCoords)
    {
        this.buttonCoords = buttonCoords;
        this.buttons = buttons;
    }

    public int update(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent event: touchEvents)
            switch(event.type)
            {
                case Input.TouchEvent.TOUCH_DOWN:
                    for (int k = 0; k < buttons.size(); k++)
                        if (!buttons.get(k).down && Assets.inBounds(new Coord(event.x,event.y),new Coord(buttonCoords[k][0],buttonCoords[k][1]), new Coord(buttonCoords[k][0]+Button.width,buttonCoords[k][1]+Button.height)))
                        {
                            buttons.get(k).down = true;
                            buttons.get(k).pointerID = event.pointer;
                        }
                    break;
                case Input.TouchEvent.TOUCH_DRAGGED:
                    for (int k = 0; k < buttons.size(); k++)
                        if (event.pointer == buttons.get(k).pointerID && !Assets.inBounds(new Coord(event.x,event.y),new Coord(buttonCoords[k][0],buttonCoords[k][1]), new Coord(buttonCoords[k][0]+Button.width,buttonCoords[k][1]+Button.height)))
                        {
                            buttons.get(k).reset();
                        }
                    break;
                case Input.TouchEvent.TOUCH_UP:
                    for (int k = 0; k < buttons.size(); k++)
                        if (event.pointer == buttons.get(k).pointerID && Assets.inBounds(new Coord(event.x,event.y),new Coord(buttonCoords[k][0],buttonCoords[k][1]), new Coord(buttonCoords[k][0]+Button.width,buttonCoords[k][1]+Button.height)))
                        {
                            for (Button b: buttons) b.reset();
                            return k;
                        }
            }

        return -1;
    }

    public void paint(Graphics g)
    {
        for (int k = 0; k < buttons.size(); k++)
        {
            Button b = buttons.get(k);
            g.drawImage(b.getImage(),buttonCoords[k][0],buttonCoords[k][1]);
            g.drawImage(b.text,buttonCoords[k][0] + (Button.width - b.w)/2,buttonCoords[k][1] + (Button.height - b.h)/2);
        }
    }

}
