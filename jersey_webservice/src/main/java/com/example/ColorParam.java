package com.example;
import java.awt.Color;
import javax.ws.rs.WebApplicationException;
import java.lang.reflect.Field;

public class ColorParam extends Color {
 
    public ColorParam(String s) {
        super(getRGB(s));
    }
 
    private static int getRGB(String s) {
        if (s.charAt(0) == '#') {
            try {
                Color c = Color.decode("0x" + s.substring(1));
                return c.getRGB();
            } catch (NumberFormatException e) {
                throw new WebApplicationException(400);
            }
        } else {
            try {
                Field f = Color.class.getField(s);
                return ((Color)f.get(null)).getRGB();
            } catch (Exception e) {
                throw new WebApplicationException(400);
            }
        }
    }
}
