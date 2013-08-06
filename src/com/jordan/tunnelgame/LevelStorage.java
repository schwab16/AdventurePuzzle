package com.jordan.tunnelgame;

public class LevelStorage {

    /*pack ids:
    0: test

    name#background#level

    TILE CHAR IDS (YOU CANT USE # or $)
    '  ' : empty space
    'f!' : finish tile

    'b+' : basic tile
    'b-' : rock tile
    'b/' : stone tile
    'b^' : snow tile
    'b&' : metal tile

    'd*' : fire tile

    's_' : ice tile

    'w0'123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ : warp tile
            (their id is as they appear, they target the # representing them )

    CHASER/ORB IDS 'a-j' 'A-J'
    'Ca' - 'Cj' : default chaser
    'Oa' - 'Oj; : default orb

    TO REMEMBER WHEN ADDING:
    1) add picture in assets folder
    2) load picture in assets class
    3) define char here
    4) make it recognised by the level class switch statement
    5) add a class that extends tile, implements methods (jordan only)
    6) getImage needs to return the correct image
    7) add functionality (in the class, in GameRunner) (jordan only)
    8) add it to a level

    HOW TO ADD A NEW BACKGROUND
    1) put it in assets folder, name it backgroundwhatever.png
    2) make it an instance variable here
    3) load it in the load method
    4) add an if statement in the backgroundByString method
    5) add it to a level after the first hashtaga
    */

    public static String getLevelString(int packID, int levelNum)
    {
        if (packID == -1) return Assets.readFromMemory(C.fileName + levelNum + ".txt");
        switch (packID * 100 + levelNum)
        {
            case -1: return ScreenLevelEditor.levelName + "#" + ScreenLevelEditor.backgroundString + "#" + ScreenLevelEditor.starString + "#" + ScreenLevelEditor.medalString + "#" + ScreenLevelEditor.levelString;
            case 1:
                return "test1#moon#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "b+b+b+          Ca              " +
                        "b+b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 2:
                return "test2#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "b+b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 3:
                return "test3#night#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "  b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 4:
                return "test4##(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "  b+b+b+b+            w0  b+b+d*" +
                        "  b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 5:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 6:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 7:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 8:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 9:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 10:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 11:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 12:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 13:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 14:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 15:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 16:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 17:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 18:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 19:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 20:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 21:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 22:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 23:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 24:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b-  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 25:
                return "le0#sunset#(200,50)$(400,50)$(1000,50)#7.77$15.01$25.40#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b-            b-b+    " +
                        "      b-b-            b-b+b+    " +
                        "          b-b-        b-        " +
                        "                                " +
                        "                                " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";

        }

        return "le0##(200,650)$(400,650)$(1000,650)#7.77$15.01$25.40#" +
                "                                " +
                "                                " +
                "            Oa                  " +
                "                                " +
                "                                " +
                "                                " +
                "                                " +
                "                                " +
                "  f!                        Ca  " +
                "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
    }

}
